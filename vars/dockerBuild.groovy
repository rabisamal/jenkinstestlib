def call(IMAGETAG)
{
    sh("docker build -t ${IMAGETAG} .")
}

