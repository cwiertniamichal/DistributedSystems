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

public interface _VariableFocalLenCameraOperationsNC extends _CameraOperationsNC
{
    void increaseFocalLength(float focalLength)
        throws FocalLengthOutOfRangeException;

    void decreaseFocalLength(float focalLength)
        throws FocalLengthOutOfRangeException;

    void setFocalLength(float focalLength)
        throws FocalLengthOutOfRangeException;
}
