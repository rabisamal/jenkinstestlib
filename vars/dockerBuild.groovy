def dockerBuild(IMAGETAG)
{
    sh("docker build -t ${IMAGETAG} .")
}

