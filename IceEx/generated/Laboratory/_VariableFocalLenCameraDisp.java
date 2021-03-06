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

public abstract class _VariableFocalLenCameraDisp extends Ice.ObjectImpl implements VariableFocalLenCamera
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
        "::Laboratory::Camera",
        "::Laboratory::Device",
        "::Laboratory::VariableFocalLenCamera"
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

    public final void rotateDown(float angle)
        throws RotationOutOfRangeException
    {
        rotateDown(angle, null);
    }

    public final void rotateLeft(float angle)
        throws RotationOutOfRangeException
    {
        rotateLeft(angle, null);
    }

    public final void rotateRight(float angle)
        throws RotationOutOfRangeException
    {
        rotateRight(angle, null);
    }

    public final void rotateTop(float angle)
        throws RotationOutOfRangeException
    {
        rotateTop(angle, null);
    }

    public final void setZoom(int zoom)
        throws ZoomOutOfRangeException
    {
        setZoom(zoom, null);
    }

    public final java.util.List<java.lang.String> getDeviceFunctions()
    {
        return getDeviceFunctions(null);
    }

    public final String getState()
    {
        return getState(null);
    }

    public final void decreaseFocalLength(float focalLength)
        throws FocalLengthOutOfRangeException
    {
        decreaseFocalLength(focalLength, null);
    }

    public final void increaseFocalLength(float focalLength)
        throws FocalLengthOutOfRangeException
    {
        increaseFocalLength(focalLength, null);
    }

    public final void setFocalLength(float focalLength)
        throws FocalLengthOutOfRangeException
    {
        setFocalLength(focalLength, null);
    }

    public static Ice.DispatchStatus ___increaseFocalLength(VariableFocalLenCamera __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        float focalLength;
        focalLength = __is.readFloat();
        __inS.endReadParams();
        try
        {
            __obj.increaseFocalLength(focalLength, __current);
            __inS.__writeEmptyParams();
            return Ice.DispatchStatus.DispatchOK;
        }
        catch(FocalLengthOutOfRangeException ex)
        {
            __inS.__writeUserException(ex, Ice.FormatType.DefaultFormat);
            return Ice.DispatchStatus.DispatchUserException;
        }
    }

    public static Ice.DispatchStatus ___decreaseFocalLength(VariableFocalLenCamera __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        float focalLength;
        focalLength = __is.readFloat();
        __inS.endReadParams();
        try
        {
            __obj.decreaseFocalLength(focalLength, __current);
            __inS.__writeEmptyParams();
            return Ice.DispatchStatus.DispatchOK;
        }
        catch(FocalLengthOutOfRangeException ex)
        {
            __inS.__writeUserException(ex, Ice.FormatType.DefaultFormat);
            return Ice.DispatchStatus.DispatchUserException;
        }
    }

    public static Ice.DispatchStatus ___setFocalLength(VariableFocalLenCamera __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        float focalLength;
        focalLength = __is.readFloat();
        __inS.endReadParams();
        try
        {
            __obj.setFocalLength(focalLength, __current);
            __inS.__writeEmptyParams();
            return Ice.DispatchStatus.DispatchOK;
        }
        catch(FocalLengthOutOfRangeException ex)
        {
            __inS.__writeUserException(ex, Ice.FormatType.DefaultFormat);
            return Ice.DispatchStatus.DispatchUserException;
        }
    }

    private final static String[] __all =
    {
        "decreaseFocalLength",
        "getDeviceFunctions",
        "getState",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "increaseFocalLength",
        "rotateDown",
        "rotateLeft",
        "rotateRight",
        "rotateTop",
        "setFocalLength",
        "setZoom"
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
                return ___decreaseFocalLength(this, in, __current);
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
                return ___ice_id(this, in, __current);
            }
            case 4:
            {
                return ___ice_ids(this, in, __current);
            }
            case 5:
            {
                return ___ice_isA(this, in, __current);
            }
            case 6:
            {
                return ___ice_ping(this, in, __current);
            }
            case 7:
            {
                return ___increaseFocalLength(this, in, __current);
            }
            case 8:
            {
                return _CameraDisp.___rotateDown(this, in, __current);
            }
            case 9:
            {
                return _CameraDisp.___rotateLeft(this, in, __current);
            }
            case 10:
            {
                return _CameraDisp.___rotateRight(this, in, __current);
            }
            case 11:
            {
                return _CameraDisp.___rotateTop(this, in, __current);
            }
            case 12:
            {
                return ___setFocalLength(this, in, __current);
            }
            case 13:
            {
                return _CameraDisp.___setZoom(this, in, __current);
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
