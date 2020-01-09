def dockerPush(IMAGETAG)
{
    sh("docker push ${IMAGETAG}")
}