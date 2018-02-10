#version 400 core

in vec2 pass_textureUV;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;

out vec4 out_Colour;

uniform sampler2D textureSampler;
uniform vec3 lightColour;
uniform float shineDamper;
uniform float reflectivity;

void main(void)
{
    vec3 unitNormal = normalize(surfaceNormal);
    vec3 unitLightVector = normalize(toLightVector);

    float nDot1 = dot(unitNormal, unitLightVector);
    float brightness = max(nDot1, 0.0);
    vec3 diffuse = brightness * lightColour;

    vec3 unitVecctorToCamera = normalize(toCameraVector);
    vec3 lightDirection = -unitLightVector;
    vec3 reflectedLightDirection = reflect(lightDirection, unitNormal);

    float specular = dot(reflectedLightDirection, unitVecctorToCamera);
    specular = max(specular, 0);
    float damped = pow(specular, shineDamper);
    vec3 finalSpecular = damped * lightColour;

    out_Colour = vec4(diffuse, 1.0) * texture(textureSampler, pass_textureUV) + vec4(finalSpecular, 0);
}