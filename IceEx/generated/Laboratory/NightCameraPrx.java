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

public interface NightCameraPrx extends CameraPrx
{
    public void switchNightModeOption();

    public void switchNightModeOption(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_switchNightModeOption();

    public Ice.AsyncResult begin_switchNightModeOption(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_switchNightModeOption(Ice.Callback __cb);

    public Ice.AsyncResult begin_switchNightModeOption(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_switchNightModeOption(Callback_NightCamera_switchNightModeOption __cb);

    public Ice.AsyncResult begin_switchNightModeOption(java.util.Map<String, String> __ctx, Callback_NightCamera_switchNightModeOption __cb);

    public Ice.AsyncResult begin_switchNightModeOption(IceInternal.Functional_VoidCallback __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_switchNightModeOption(IceInternal.Functional_VoidCallback __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                       IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_switchNightModeOption(java.util.Map<String, String> __ctx, 
                                                       IceInternal.Functional_VoidCallback __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_switchNightModeOption(java.util.Map<String, String> __ctx, 
                                                       IceInternal.Functional_VoidCallback __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                       IceInternal.Functional_BoolCallback __sentCb);

    public void end_switchNightModeOption(Ice.AsyncResult __result);
}
