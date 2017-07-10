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

public interface ReporterPrx extends Ice.ObjectPrx
{
    public void report(String message);

    public void report(String message, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_report(String message);

    public Ice.AsyncResult begin_report(String message, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_report(String message, Ice.Callback __cb);

    public Ice.AsyncResult begin_report(String message, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_report(String message, Callback_Reporter_report __cb);

    public Ice.AsyncResult begin_report(String message, java.util.Map<String, String> __ctx, Callback_Reporter_report __cb);

    public Ice.AsyncResult begin_report(String message, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_report(String message, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_report(String message, 
                                        java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_report(String message, 
                                        java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb);

    public void end_report(Ice.AsyncResult __result);
}