package com.andrewtremblay.openglandroidcamera;

public final class MyGLShaders {
    public static final String mExampleVertexShader =
        "uniform mat4 uMVPMatrix;\n" +
        "uniform mat4 uSTMatrix;\n" +
        "uniform float uCRatio;\n" +
        "attribute vec4 aPosition;\n" +
        "attribute vec4 aTextureCoord;\n" +
        "varying vec2 vTextureCoord;\n" +
        "varying vec2 vTextureNormCoord;\n" +
        "void main() {\n" +
        "  vec4 scaledPos = aPosition;\n" +
        "  scaledPos.x = scaledPos.x * uCRatio;\n" +
        "  gl_Position = uMVPMatrix * scaledPos;\n" +
        "  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n" +
        "  vTextureNormCoord = aTextureCoord.xy;\n" +
        "}\n";
    public static final String mExampleFragmentShader =
        "#extension GL_OES_EGL_image_external : require\n" +
        "precision mediump float;\n" +
        "varying vec2 vTextureCoord;\n" +
        "varying vec2 vTextureNormCoord;\n" +
        "uniform samplerExternalOES sTexture;\n" +
        "void main() {\n" +
        "  gl_FragColor = texture2D(sTexture, vTextureCoord);\n" +
        "  gl_FragColor.a = 1.0-min(length(vTextureNormCoord-0.5)*2.0,1.0);\n" +
        "}\n";
}
