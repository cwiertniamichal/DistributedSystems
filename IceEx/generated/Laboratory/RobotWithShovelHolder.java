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

public final class RobotWithShovelHolder extends Ice.ObjectHolderBase<RobotWithShovel>
{
    public
    RobotWithShovelHolder()
    {
    }

    public
    RobotWithShovelHolder(RobotWithShovel value)
    {
        this.value = value;
    }

    public void
    patch(Ice.Object v)
    {
        if(v == null || v instanceof RobotWithShovel)
        {
            value = (RobotWithShovel)v;
        }
        else
        {
            IceInternal.Ex.throwUOE(type(), v);
        }
    }

    public String
    type()
    {
        return _RobotWithShovelDisp.ice_staticId();
    }
}
