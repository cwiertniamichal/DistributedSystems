// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.3
//
// <auto-generated>
//
// Generated from file `Laboratory.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Laboratory;

public abstract class _RobotWithArmDisp extends Ice.ObjectImpl implements RobotWithArm
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::Laboratory::Device",
        "::Laboratory::Robot",
        "::Laboratory::RobotWithArm"
    };

    public boolean ice_isA(String s)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean ice_isA(String s, Ice.Current __current)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[] ice_ids()
    {
        return __ids;
    }

    public String[] ice_ids(Ice.Current __current)
    {
        return __ids;
    }

    public String ice_id()
    {
        return __ids[3];
    }

    public String ice_id(Ice.Current __current)
    {
        return __ids[3];
    }

    public static String ice_staticId()
    {
        return __ids[3];
    }

    public final java.util.List<java.lang.String> getDeviceFunctions()
    {
        return getDeviceFunctions(null);
    }

    public final String getState()
    {
        return getState(null);
    }

    public final void moveTo(Point point)
        throws BadRobotStateException
    {
        moveTo(point, null);
    }

    public final void turnOff()
        throws BadRobotStateException
    {
        turnOff(null);
    }

    public final void turnOn()
        throws BadRobotStateException
    {
        turnOn(null);
    }

    public final void dropItem()
        throws BadArmStateException,
               BadRobotStateException
    {
        dropItem(null);
    }

    public final void grabItem(int power)
        throws BadArmStateException,
               BadRobotStateException
    {
        grabItem(power, null);
    }

    public final void pocketArm()
        throws BadArmStateException,
               BadRobotStateException
    {
        pocketArm(null);
    }

    public final void releaseArm(int distance)
        throws ArmLengthOutOfRangeException,
               BadRobotStateException
    {
        releaseArm(distance, null);
    }

    public static Ice.DispatchStatus ___releaseArm(RobotWithArm __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        int distance;
        distance = __is.readInt();
        __inS.endReadParams();
        try
        {
            __obj.releaseArm(distance, __current);
            __inS.__writeEmptyParams();
            return Ice.DispatchStatus.DispatchOK;
        }
        catch(ArmLengthOutOfRangeException ex)
        {
            __inS.__writeUserException(ex, Ice.FormatType.DefaultFormat);
            return Ice.DispatchStatus.DispatchUserException;
        }
        catch(BadRobotStateException ex)
        {
            __inS.__writeUserException(ex, Ice.FormatType.DefaultFormat);
            return Ice.DispatchStatus.DispatchUserException;
        }
    }

    public static Ice.DispatchStatus ___pocketArm(RobotWithArm __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        __inS.readEmptyParams();
        try
        {
            __obj.pocketArm(__current);
            __inS.__writeEmptyParams();
            return Ice.DispatchStatus.DispatchOK;
        }
        catch(BadArmStateException ex)
        {
            __inS.__writeUserException(ex, Ice.FormatType.DefaultFormat);
            return Ice.DispatchStatus.DispatchUserException;
        }
        catch(BadRobotStateException ex)
        {
            __inS.__writeUserException(ex, Ice.FormatType.DefaultFormat);
            return Ice.DispatchStatus.DispatchUserException;
        }
    }

    public static Ice.DispatchStatus ___grabItem(RobotWithArm __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        int power;
        power = __is.readInt();
        __inS.endReadParams();
        try
        {
            __obj.grabItem(power, __current);
            __inS.__writeEmptyParams();
            return Ice.DispatchStatus.DispatchOK;
        }
        catch(BadArmStateException ex)
        {
            __inS.__writeUserException(ex, Ice.FormatType.DefaultFormat);
            return Ice.DispatchStatus.DispatchUserException;
        }
        catch(BadRobotStateException ex)
        {
            __inS.__writeUserException(ex, Ice.FormatType.DefaultFormat);
            return Ice.DispatchStatus.DispatchUserException;
        }
    }

    public static Ice.DispatchStatus ___dropItem(RobotWithArm __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        __inS.readEmptyParams();
        try
        {
            __obj.dropItem(__current);
            __inS.__writeEmptyParams();
            return Ice.DispatchStatus.DispatchOK;
        }
        catch(BadArmStateException ex)
        {
            __inS.__writeUserException(ex, Ice.FormatType.DefaultFormat);
            return Ice.DispatchStatus.DispatchUserException;
        }
        catch(BadRobotStateException ex)
        {
            __inS.__writeUserException(ex, Ice.FormatType.DefaultFormat);
            return Ice.DispatchStatus.DispatchUserException;
        }
    }

    private final static String[] __all =
    {
        "dropItem",
        "getDeviceFunctions",
        "getState",
        "grabItem",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "moveTo",
        "pocketArm",
        "releaseArm",
        "turnOff",
        "turnOn"
    };

    public Ice.DispatchStatus __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
        int pos = java.util.Arrays.binarySearch(__all, __current.operation);
        if(pos < 0)
        {
            throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return ___dropItem(this, in, __current);
            }
            case 1:
            {
                return _DeviceDisp.___getDeviceFunctions(this, in, __current);
            }
            case 2:
            {
                return _DeviceDisp.___getState(this, in, __current);
            }
            case 3:
            {
                return ___grabItem(this, in, __current);
            }
            case 4:
            {
                return ___ice_id(this, in, __current);
            }
            case 5:
            {
                return ___ice_ids(this, in, __current);
            }
            case 6:
            {
                return ___ice_isA(this, in, __current);
            }
            case 7:
            {
                return ___ice_ping(this, in, __current);
            }
            case 8:
            {
                return _RobotDisp.___moveTo(this, in, __current);
            }
            case 9:
            {
                return ___pocketArm(this, in, __current);
            }
            case 10:
            {
                return ___releaseArm(this, in, __current);
            }
            case 11:
            {
                return _RobotDisp.___turnOff(this, in, __current);
            }
            case 12:
            {
                return _RobotDisp.___turnOn(this, in, __current);
            }
        }

        assert(false);
        throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
    }

    protected void __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice(ice_staticId(), -1, true);
        __os.endWriteSlice();
    }

    protected void __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        __is.endReadSlice();
    }

    public static final long serialVersionUID = 0L;
}
