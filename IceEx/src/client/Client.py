import sys
import traceback
import Ice
import IceStorm
import Laboratory
import ReporterI
import re


def get_username():
    print("Please enter your name: ")
    name = input()
    print("Hello {}. Welcome in our laboratory.".format(name))
    return name


def print_help():
    print("Available options: \n"
          "-t - take control over device \n"
          "-r - release device \n"
          "-a - perform action on device \n"
          "-o - start observing device \n"
          "-s - stop observing device \n"
          "-l - list all devices in laboratory \n"
          "-f - list device's functions \n"
          "-c - list all controlled devices \n"
          "-p - list all observed devices \n"
          "-h - print this help again \n"
          "-q - exit \n")


def print_devices_in_laboratory():
    print("Devices in laboratory:")
    for device_name in devices_in_laboratory:
        print("{}".format(device_name))


def print_controlled_devices():
    print("Controlled devices:")
    for device_name in controlled_devices:
        print("{}".format(device_name))


def print_observed_devices():
    print("Observed devices:")
    for device_name in observed_devices.keys():
        print("{}".format(device_name))


def create_devices_topics(devices_names_list):
    for device_name in devices_names_list:
        try:
            topic_manager.create(device_name)
        except IceStorm.TopicExists:
            pass


def subscribe_to_topic(device_name):
    reporter = ReporterI.ReporterI()
    proxy = reporterAdapter.addWithUUID(reporter).ice_oneway()
    reporterAdapter.activate()
    try:
        topic = topic_manager.retrieve(device_name)
        qos = None
        topic.subscribeAndGetPublisher(qos, proxy)
        observed_devices[device_name] = (topic, proxy)
        print("Started observing device: {}".format(device_name))
    except IceStorm.NoSuchTopic:
        print("Invalid topic name")


def start_observing():
    print("Enter device name you want to start observing: ")
    device = input()
    if device in observed_devices:
        print("You already observing that device. Returning to main menu.")
        return
    subscribe_to_topic(device)


def stop_observing():
    print("Enter device you want to stop observing: ")
    device = input()
    try:
        topic, proxy = observed_devices[device]
        topic.unsubscribe(proxy)
        observed_devices.__delitem__(device)
        print("You are not observing device: {} anymore".format(device))
    except KeyError:
        print("You do not observe such device. Returning to main menu.")


def take_control_over_device():
    print("Enter name of device you want to control: ")
    device = input()
    if device not in devices_in_laboratory:
        print("There is no such device in laboratory")
        return
    if device in controlled_devices:
        print("You already control that device")
        return
    try:
        proxy = lab.takeControlOverDevice(device, username)
        controlled_devices[device] = proxy
    except:
        traceback.print_exc()
        return
    topic = topic_manager.retrieve(device)
    pub = topic.getPublisher().ice_oneway()
    reporter = Laboratory.ReporterPrx.uncheckedCast(pub)
    reporter.report("User: {} has taken control over device: {}".format(username, device))
    print("Successfully locked {}".format(device))


def release_device():
    print("Enter name of device you want to release: ")
    device = input()
    if device not in controlled_devices:
        print("You do not control this device. Returning to main menu.")
        return
    try:
        lab.releaseDevice(device, username)
    except:
        traceback.print_exc()
        return
    controlled_devices.__delitem__(device)
    topic = topic_manager.retrieve(device)
    pub = topic.getPublisher().ice_oneway()
    reporter = Laboratory.ReporterPrx.uncheckedCast(pub)
    reporter.report("User: {} released device: {}".format(username, device))
    print("Released device: {}".format(device))


def print_functions(function_list):
    for i in range(len(function_list)):
        print("{}: {}".format(i, function_list[i]))


def control_device():
    device = input("Enter name of device you want to control: \n")
    if device not in controlled_devices:
        print("You do not control such device. Returning to main menu.")
        return
    try:
        topic = topic_manager.retrieve(device)
    except IceStorm.NoSuchTopic:
        print("There is no such device on the server. Returning to main menu.")
        return
    device_functions = controlled_devices[device].getDeviceFunctions()
    print_functions(device_functions)
    function = input("Enter number of function you want to perform: \n")
    try:
        parsed = re.split("\(|\)", device_functions[int(function)])
    except:
        print("Not valid operation index. Returning to main menu.")
        return
    function_name = parsed[0]
    args = []
    try:
        for i in range(1, len(parsed)):
            if len(parsed[i]) == 0:
                continue
            arg_type = parsed[i].split(" ")[0]
            if arg_type in basic_types:
                arg = input("Enter value for argument: {}\n".format(parsed[i]))
                arg = eval(arg_type + "(arg)")
                args.append(arg)
            else:
                arg = eval("Laboratory." + arg_type + "()")
                for field in vars(arg):
                    value = input("Enter value for {}.{}\n".format(arg_type, field))
                    exec("arg." + field + "=" + str(value))
                args.append(arg)
    except:
        print("Invalid value. Returning to menu.")
        return
    try:
        most_derived_proxy_type = controlled_devices[device].ice_id()
        proxy_type_parsed = eval(most_derived_proxy_type[2:].replace("::", ".") +
                                 "Prx.checkedCast(controlled_devices[device])")
        res = getattr(proxy_type_parsed, function_name)(*args)
        if res:
            print(res)
        state = controlled_devices[device].getState()
        print("Action {} performed. New state of device: {} is:\n{}".format(function_name, device, state))
        pub = topic.getPublisher().ice_oneway()
        reporter = Laboratory.ReporterPrx.uncheckedCast(pub)
        reporter.report("User: {} changed state of device: {} to: \n{}".format(username, device, state))
    except:
        traceback.print_exc()


def print_devices_functions():
    device = input("Enter name of device for which you want to list functions: \n")
    if device not in devices_in_laboratory:
        print("There is no such device in laboratory. Returning to menu.")
        return
    device_functions = lab.getAvailableFunctionsForDevice(device)
    print_functions(device_functions)


def at_exit():
    for device in controlled_devices:
        lab.releaseDevice(device, username)
    for device in observed_devices:
        topic, proxy = observed_devices[device]
        topic.unsubscribe(proxy)
        observed_devices.__delitem__(device)


status = 0
ic = None
basic_types = ["int", "float", "string"]
try:
    # init ICE
    ic = Ice.initialize(sys.argv)

    # get topic manager
    topic_manager_base = ic.propertyToProxy("Client.TopicManager")
    topic_manager = IceStorm.TopicManagerPrx.checkedCast(topic_manager_base)

    if topic_manager is None:
        raise RuntimeError("Invalid topic manager")

    # get proxies
    lab_base = ic.propertyToProxy("Client.LabProxy")

    lab = Laboratory.LabPrx.checkedCast(lab_base)

    if not lab:
        raise RuntimeError("Invalid proxy")

    # get available devices
    devices_in_laboratory = lab.getDevices()

    # create topic for each device
    create_devices_topics(devices_in_laboratory)

    # create reporter adapter
    reporterAdapter = ic.createObjectAdapter("reporterAdapter")

    controlled_devices = {}
    observed_devices = {}

    username = get_username()
    print_help()

    while True:
        option = input()
        if option == "-t":
            take_control_over_device()
        elif option == "-r":
            release_device()
        elif option == "-o":
            start_observing()
        elif option == "-s":
            stop_observing()
        elif option == "-l":
            print_devices_in_laboratory()
        elif option == "-h":
            print_help()
        elif option == "-c":
            print_controlled_devices()
        elif option == "-p":
            print_observed_devices()
        elif option == "-a":
            control_device()
        elif option == "-f":
            print_devices_functions()
        elif option == "-q":
            at_exit()
            break
        else:
            print("Unknown option")
except:
    traceback.print_exc()
    status = 1

if ic:
    # Clean up
    try:
        ic.destroy()
    except:
        traceback.print_exc()
        status = 1

sys.exit(status)
