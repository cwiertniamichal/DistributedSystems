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

public interface RobotPrx extends DevicePrx
{
    public void turnOn()
        throws BadRobotStateException;

    public void turnOn(java.util.Map<String, String> __ctx)
        throws BadRobotStateException;

    public Ice.AsyncResult begin_turnOn();

    public Ice.AsyncResult begin_turnOn(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_turnOn(Ice.Callback __cb);

    public Ice.AsyncResult begin_turnOn(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_turnOn(Callback_Robot_turnOn __cb);

    public Ice.AsyncResult begin_turnOn(java.util.Map<String, String> __ctx, Callback_Robot_turnOn __cb);

    public Ice.AsyncResult begin_turnOn(IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_turnOn(IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_turnOn(java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_turnOn(java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb);

    public void end_turnOn(Ice.AsyncResult __result)
        throws BadRobotStateException;

    public void turnOff()
        throws BadRobotStateException;

    public void turnOff(java.util.Map<String, String> __ctx)
        throws BadRobotStateException;

    public Ice.AsyncResult begin_turnOff();

    public Ice.AsyncResult begin_turnOff(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_turnOff(Ice.Callback __cb);

    public Ice.AsyncResult begin_turnOff(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_turnOff(Callback_Robot_turnOff __cb);

    public Ice.AsyncResult begin_turnOff(java.util.Map<String, String> __ctx, Callback_Robot_turnOff __cb);

    public Ice.AsyncResult begin_turnOff(IceInternal.Functional_VoidCallback __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_turnOff(IceInternal.Functional_VoidCallback __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                         IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_turnOff(java.util.Map<String, String> __ctx, 
                                         IceInternal.Functional_VoidCallback __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_turnOff(java.util.Map<String, String> __ctx, 
                                         IceInternal.Functional_VoidCallback __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                         IceInternal.Functional_BoolCallback __sentCb);

    public void end_turnOff(Ice.AsyncResult __result)
        throws BadRobotStateException;

    public void moveTo(Point point)
        throws BadRobotStateException;

    public void moveTo(Point point, java.util.Map<String, String> __ctx)
        throws BadRobotStateException;

    public Ice.AsyncResult begin_moveTo(Point point);

    public Ice.AsyncResult begin_moveTo(Point point, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_moveTo(Point point, Ice.Callback __cb);

    public Ice.AsyncResult begin_moveTo(Point point, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_moveTo(Point point, Callback_Robot_moveTo __cb);

    public Ice.AsyncResult begin_moveTo(Point point, java.util.Map<String, String> __ctx, Callback_Robot_moveTo __cb);

    public Ice.AsyncResult begin_moveTo(Point point, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_moveTo(Point point, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_moveTo(Point point, 
                                        java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_moveTo(Point point, 
                                        java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb);

    public void end_moveTo(Ice.AsyncResult __result)
        throws BadRobotStateException;
}
