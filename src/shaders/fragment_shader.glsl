#version 400 core

in vec2 pass_textureUV;

out vec4 out_Colour;

uniform sampler2D textureSampler;

void main(void)
{
    out_Colour = texture(textureSampler, pass_textureUV);
}