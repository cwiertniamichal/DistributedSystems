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

public abstract class Callback_Robot_turnOn
    extends IceInternal.TwowayCallback implements Ice.TwowayCallbackVoidUE
{
    public final void __completed(Ice.AsyncResult __result)
    {
        RobotPrxHelper.__turnOn_completed(this, __result);
    }
}
