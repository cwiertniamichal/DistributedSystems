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

/**
 * Provides type-specific helper functions.
 **/
public final class RobotPrxHelper extends Ice.ObjectPrxHelperBase implements RobotPrx
{
    private static final String __getDeviceFunctions_name = "getDeviceFunctions";

    public java.util.List<java.lang.String> getDeviceFunctions()
    {
        return getDeviceFunctions(null, false);
    }

    public java.util.List<java.lang.String> getDeviceFunctions(java.util.Map<String, String> __ctx)
    {
        return getDeviceFunctions(__ctx, true);
    }

    private java.util.List<java.lang.String> getDeviceFunctions(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__getDeviceFunctions_name);
        return end_getDeviceFunctions(begin_getDeviceFunctions(__ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_getDeviceFunctions()
    {
        return begin_getDeviceFunctions(null, false, false, null);
    }

    public Ice.AsyncResult begin_getDeviceFunctions(java.util.Map<String, String> __ctx)
    {
        return begin_getDeviceFunctions(__ctx, true, false, null);
    }

    public Ice.AsyncResult begin_getDeviceFunctions(Ice.Callback __cb)
    {
        return begin_getDeviceFunctions(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getDeviceFunctions(java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_getDeviceFunctions(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getDeviceFunctions(Callback_Device_getDeviceFunctions __cb)
    {
        return begin_getDeviceFunctions(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getDeviceFunctions(java.util.Map<String, String> __ctx, Callback_Device_getDeviceFunctions __cb)
    {
        return begin_getDeviceFunctions(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getDeviceFunctions(IceInternal.Functional_GenericCallback1<java.util.List<java.lang.String>> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getDeviceFunctions(null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getDeviceFunctions(IceInternal.Functional_GenericCallback1<java.util.List<java.lang.String>> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getDeviceFunctions(null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_getDeviceFunctions(java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<java.util.List<java.lang.String>> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getDeviceFunctions(__ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getDeviceFunctions(java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<java.util.List<java.lang.String>> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getDeviceFunctions(__ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_getDeviceFunctions(java.util.Map<String, String> __ctx, 
                                                     boolean __explicitCtx, 
                                                     boolean __synchronous, 
                                                     IceInternal.Functional_GenericCallback1<java.util.List<java.lang.String>> __responseCb, 
                                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                     IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getDeviceFunctions(__ctx, __explicitCtx, __synchronous, 
                                        new IceInternal.Functional_TwowayCallbackArg1<java.util.List<java.lang.String>>(__responseCb, __exceptionCb, __sentCb)
                                            {
                                                public final void __completed(Ice.AsyncResult __result)
                                                {
                                                    RobotPrxHelper.__getDeviceFunctions_completed(this, __result);
                                                }
                                            });
    }

    private Ice.AsyncResult begin_getDeviceFunctions(java.util.Map<String, String> __ctx, 
                                                     boolean __explicitCtx, 
                                                     boolean __synchronous, 
                                                     IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__getDeviceFunctions_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__getDeviceFunctions_name, __cb);
        try
        {
            __result.prepare(__getDeviceFunctions_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            __result.writeEmptyParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public java.util.List<java.lang.String> end_getDeviceFunctions(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __getDeviceFunctions_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            java.util.List<java.lang.String> __ret;
            __ret = StringListHelper.read(__is);
            __result.endReadParams();
            return __ret;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __getDeviceFunctions_completed(Ice.TwowayCallbackArg1<java.util.List<java.lang.String>> __cb, Ice.AsyncResult __result)
    {
        Laboratory.DevicePrx __proxy = (Laboratory.DevicePrx)__result.getProxy();
        java.util.List<java.lang.String> __ret = null;
        try
        {
            __ret = __proxy.end_getDeviceFunctions(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    private static final String __getState_name = "getState";

    public String getState()
    {
        return getState(null, false);
    }

    public String getState(java.util.Map<String, String> __ctx)
    {
        return getState(__ctx, true);
    }

    private String getState(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__getState_name);
        return end_getState(begin_getState(__ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_getState()
    {
        return begin_getState(null, false, false, null);
    }

    public Ice.AsyncResult begin_getState(java.util.Map<String, String> __ctx)
    {
        return begin_getState(__ctx, true, false, null);
    }

    public Ice.AsyncResult begin_getState(Ice.Callback __cb)
    {
        return begin_getState(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getState(java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_getState(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getState(Callback_Device_getState __cb)
    {
        return begin_getState(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getState(java.util.Map<String, String> __ctx, Callback_Device_getState __cb)
    {
        return begin_getState(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getState(IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getState(null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getState(IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getState(null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_getState(java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getState(__ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getState(java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getState(__ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_getState(java.util.Map<String, String> __ctx, 
                                           boolean __explicitCtx, 
                                           boolean __synchronous, 
                                           IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                           IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getState(__ctx, __explicitCtx, __synchronous, 
                              new IceInternal.Functional_TwowayCallbackArg1<String>(__responseCb, __exceptionCb, __sentCb)
                                  {
                                      public final void __completed(Ice.AsyncResult __result)
                                      {
                                          RobotPrxHelper.__getState_completed(this, __result);
                                      }
                                  });
    }

    private Ice.AsyncResult begin_getState(java.util.Map<String, String> __ctx, 
                                           boolean __explicitCtx, 
                                           boolean __synchronous, 
                                           IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__getState_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__getState_name, __cb);
        try
        {
            __result.prepare(__getState_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            __result.writeEmptyParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public String end_getState(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __getState_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            String __ret;
            __ret = __is.readString();
            __result.endReadParams();
            return __ret;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __getState_completed(Ice.TwowayCallbackArg1<String> __cb, Ice.AsyncResult __result)
    {
        Laboratory.DevicePrx __proxy = (Laboratory.DevicePrx)__result.getProxy();
        String __ret = null;
        try
        {
            __ret = __proxy.end_getState(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    private static final String __moveTo_name = "moveTo";

    public void moveTo(Point point)
        throws BadRobotStateException
    {
        moveTo(point, null, false);
    }

    public void moveTo(Point point, java.util.Map<String, String> __ctx)
        throws BadRobotStateException
    {
        moveTo(point, __ctx, true);
    }

    private void moveTo(Point point, java.util.Map<String, String> __ctx, boolean __explicitCtx)
        throws BadRobotStateException
    {
        __checkTwowayOnly(__moveTo_name);
        end_moveTo(begin_moveTo(point, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_moveTo(Point point)
    {
        return begin_moveTo(point, null, false, false, null);
    }

    public Ice.AsyncResult begin_moveTo(Point point, java.util.Map<String, String> __ctx)
    {
        return begin_moveTo(point, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_moveTo(Point point, Ice.Callback __cb)
    {
        return begin_moveTo(point, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_moveTo(Point point, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_moveTo(point, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_moveTo(Point point, Callback_Robot_moveTo __cb)
    {
        return begin_moveTo(point, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_moveTo(Point point, java.util.Map<String, String> __ctx, Callback_Robot_moveTo __cb)
    {
        return begin_moveTo(point, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_moveTo(Point point, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_moveTo(point, null, false, false, __responseCb, __userExceptionCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_moveTo(Point point, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_moveTo(point, null, false, false, __responseCb, __userExceptionCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_moveTo(Point point, 
                                        java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_moveTo(point, __ctx, true, false, __responseCb, __userExceptionCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_moveTo(Point point, 
                                        java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_moveTo(point, __ctx, true, false, __responseCb, __userExceptionCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_moveTo(Point point, 
                                         java.util.Map<String, String> __ctx, 
                                         boolean __explicitCtx, 
                                         boolean __synchronous, 
                                         IceInternal.Functional_VoidCallback __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                         IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_moveTo(point, __ctx, __explicitCtx, __synchronous, 
                            new IceInternal.Functional_TwowayCallbackVoidUE(__responseCb, __userExceptionCb, __exceptionCb, __sentCb)
                                {
                                    public final void __completed(Ice.AsyncResult __result)
                                    {
                                        RobotPrxHelper.__moveTo_completed(this, __result);
                                    }
                                });
    }

    private Ice.AsyncResult begin_moveTo(Point point, 
                                         java.util.Map<String, String> __ctx, 
                                         boolean __explicitCtx, 
                                         boolean __synchronous, 
                                         IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__moveTo_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__moveTo_name, __cb);
        try
        {
            __result.prepare(__moveTo_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            Point.__write(__os, point);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public void end_moveTo(Ice.AsyncResult __iresult)
        throws BadRobotStateException
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __moveTo_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(BadRobotStateException __ex)
                {
                    throw __ex;
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            __result.readEmptyParams();
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __moveTo_completed(Ice.TwowayCallbackVoidUE __cb, Ice.AsyncResult __result)
    {
        Laboratory.RobotPrx __proxy = (Laboratory.RobotPrx)__result.getProxy();
        try
        {
            __proxy.end_moveTo(__result);
        }
        catch(Ice.UserException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response();
    }

    private static final String __turnOff_name = "turnOff";

    public void turnOff()
        throws BadRobotStateException
    {
        turnOff(null, false);
    }

    public void turnOff(java.util.Map<String, String> __ctx)
        throws BadRobotStateException
    {
        turnOff(__ctx, true);
    }

    private void turnOff(java.util.Map<String, String> __ctx, boolean __explicitCtx)
        throws BadRobotStateException
    {
        __checkTwowayOnly(__turnOff_name);
        end_turnOff(begin_turnOff(__ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_turnOff()
    {
        return begin_turnOff(null, false, false, null);
    }

    public Ice.AsyncResult begin_turnOff(java.util.Map<String, String> __ctx)
    {
        return begin_turnOff(__ctx, true, false, null);
    }

    public Ice.AsyncResult begin_turnOff(Ice.Callback __cb)
    {
        return begin_turnOff(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_turnOff(java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_turnOff(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_turnOff(Callback_Robot_turnOff __cb)
    {
        return begin_turnOff(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_turnOff(java.util.Map<String, String> __ctx, Callback_Robot_turnOff __cb)
    {
        return begin_turnOff(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_turnOff(IceInternal.Functional_VoidCallback __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_turnOff(null, false, false, __responseCb, __userExceptionCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_turnOff(IceInternal.Functional_VoidCallback __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                         IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_turnOff(null, false, false, __responseCb, __userExceptionCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_turnOff(java.util.Map<String, String> __ctx, 
                                         IceInternal.Functional_VoidCallback __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_turnOff(__ctx, true, false, __responseCb, __userExceptionCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_turnOff(java.util.Map<String, String> __ctx, 
                                         IceInternal.Functional_VoidCallback __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                         IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_turnOff(__ctx, true, false, __responseCb, __userExceptionCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_turnOff(java.util.Map<String, String> __ctx, 
                                          boolean __explicitCtx, 
                                          boolean __synchronous, 
                                          IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_turnOff(__ctx, __explicitCtx, __synchronous, 
                             new IceInternal.Functional_TwowayCallbackVoidUE(__responseCb, __userExceptionCb, __exceptionCb, __sentCb)
                                 {
                                     public final void __completed(Ice.AsyncResult __result)
                                     {
                                         RobotPrxHelper.__turnOff_completed(this, __result);
                                     }
                                 });
    }

    private Ice.AsyncResult begin_turnOff(java.util.Map<String, String> __ctx, 
                                          boolean __explicitCtx, 
                                          boolean __synchronous, 
                                          IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__turnOff_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__turnOff_name, __cb);
        try
        {
            __result.prepare(__turnOff_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            __result.writeEmptyParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public void end_turnOff(Ice.AsyncResult __iresult)
        throws BadRobotStateException
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __turnOff_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(BadRobotStateException __ex)
                {
                    throw __ex;
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            __result.readEmptyParams();
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __turnOff_completed(Ice.TwowayCallbackVoidUE __cb, Ice.AsyncResult __result)
    {
        Laboratory.RobotPrx __proxy = (Laboratory.RobotPrx)__result.getProxy();
        try
        {
            __proxy.end_turnOff(__result);
        }
        catch(Ice.UserException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response();
    }

    private static final String __turnOn_name = "turnOn";

    public void turnOn()
        throws BadRobotStateException
    {
        turnOn(null, false);
    }

    public void turnOn(java.util.Map<String, String> __ctx)
        throws BadRobotStateException
    {
        turnOn(__ctx, true);
    }

    private void turnOn(java.util.Map<String, String> __ctx, boolean __explicitCtx)
        throws BadRobotStateException
    {
        __checkTwowayOnly(__turnOn_name);
        end_turnOn(begin_turnOn(__ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_turnOn()
    {
        return begin_turnOn(null, false, false, null);
    }

    public Ice.AsyncResult begin_turnOn(java.util.Map<String, String> __ctx)
    {
        return begin_turnOn(__ctx, true, false, null);
    }

    public Ice.AsyncResult begin_turnOn(Ice.Callback __cb)
    {
        return begin_turnOn(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_turnOn(java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_turnOn(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_turnOn(Callback_Robot_turnOn __cb)
    {
        return begin_turnOn(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_turnOn(java.util.Map<String, String> __ctx, Callback_Robot_turnOn __cb)
    {
        return begin_turnOn(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_turnOn(IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_turnOn(null, false, false, __responseCb, __userExceptionCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_turnOn(IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_turnOn(null, false, false, __responseCb, __userExceptionCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_turnOn(java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_turnOn(__ctx, true, false, __responseCb, __userExceptionCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_turnOn(java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_VoidCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_turnOn(__ctx, true, false, __responseCb, __userExceptionCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_turnOn(java.util.Map<String, String> __ctx, 
                                         boolean __explicitCtx, 
                                         boolean __synchronous, 
                                         IceInternal.Functional_VoidCallback __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.UserException> __userExceptionCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                         IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_turnOn(__ctx, __explicitCtx, __synchronous, 
                            new IceInternal.Functional_TwowayCallbackVoidUE(__responseCb, __userExceptionCb, __exceptionCb, __sentCb)
                                {
                                    public final void __completed(Ice.AsyncResult __result)
                                    {
                                        RobotPrxHelper.__turnOn_completed(this, __result);
                                    }
                                });
    }

    private Ice.AsyncResult begin_turnOn(java.util.Map<String, String> __ctx, 
                                         boolean __explicitCtx, 
                                         boolean __synchronous, 
                                         IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__turnOn_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__turnOn_name, __cb);
        try
        {
            __result.prepare(__turnOn_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            __result.writeEmptyParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public void end_turnOn(Ice.AsyncResult __iresult)
        throws BadRobotStateException
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __turnOn_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(BadRobotStateException __ex)
                {
                    throw __ex;
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            __result.readEmptyParams();
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __turnOn_completed(Ice.TwowayCallbackVoidUE __cb, Ice.AsyncResult __result)
    {
        Laboratory.RobotPrx __proxy = (Laboratory.RobotPrx)__result.getProxy();
        try
        {
            __proxy.end_turnOn(__result);
        }
        catch(Ice.UserException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response();
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static RobotPrx checkedCast(Ice.ObjectPrx __obj)
    {
        return checkedCastImpl(__obj, ice_staticId(), RobotPrx.class, RobotPrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static RobotPrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __ctx, ice_staticId(), RobotPrx.class, RobotPrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static RobotPrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return checkedCastImpl(__obj, __facet, ice_staticId(), RobotPrx.class, RobotPrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static RobotPrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __facet, __ctx, ice_staticId(), RobotPrx.class, RobotPrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @return A proxy for this type.
     **/
    public static RobotPrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        return uncheckedCastImpl(__obj, RobotPrx.class, RobotPrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    public static RobotPrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return uncheckedCastImpl(__obj, __facet, RobotPrx.class, RobotPrxHelper.class);
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::Laboratory::Device",
        "::Laboratory::Robot"
    };

    /**
     * Provides the Slice type ID of this type.
     * @return The Slice type ID.
     **/
    public static String ice_staticId()
    {
        return __ids[2];
    }

    public static void __write(IceInternal.BasicStream __os, RobotPrx v)
    {
        __os.writeProxy(v);
    }

    public static RobotPrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            RobotPrxHelper result = new RobotPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}