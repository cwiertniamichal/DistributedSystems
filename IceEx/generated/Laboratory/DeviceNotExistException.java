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

public class DeviceNotExistException extends Ice.UserException
{
    public DeviceNotExistException()
    {
        info = "";
    }

    public DeviceNotExistException(Throwable __cause)
    {
        super(__cause);
        info = "";
    }

    public DeviceNotExistException(String info)
    {
        this.info = info;
    }

    public DeviceNotExistException(String info, Throwable __cause)
    {
        super(__cause);
        this.info = info;
    }

    public String
    ice_name()
    {
        return "Laboratory::DeviceNotExistException";
    }

    public String info;

    protected void
    __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice("::Laboratory::DeviceNotExistException", -1, true);
        __os.writeString(info);
        __os.endWriteSlice();
    }

    protected void
    __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        info = __is.readString();
        __is.endReadSlice();
    }

    public static final long serialVersionUID = 469112392L;
}