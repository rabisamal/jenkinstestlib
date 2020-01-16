def call()
{
    script {

        // Read POM xml file using 'readMavenPom' step , this step 'readMavenPom' is included in: https://plugins.jenkins.io/pipeline-utility-steps

        pom = readMavenPom file: "pom.xml";

        // Find built artifact under target folder

        filesByGlob = findFiles(glob: "target/*.${pom.packaging}");

        // Print some info from the artifact found

        echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"

        // Extract the path from the File found

        artifactPath = filesByGlob[0].path;

        // Assign to a boolean response verifying If the artifact name exists

        artifactExists = fileExists artifactPath;

        if(artifactExists) {

            echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";

            nexusPublisher
        nexusInstanceId: 'mynexus',
        nexusRepositoryId: 'nexus_repo',
        packages: [
                [$class: 'MavenPackage',
                 mavenAssetList: [
                         [classifier: '',
                          extension: '',
                          filePath: 'artifactPath']],

                 mavenCoordinate: [artifactId: 'pom.packaging',
                                   groupId: 'pom.groupId',
                                   packaging: 'pom.packaging',
                                   version: 'pom.version']]]

        } else {

            error "*** File: ${artifactPath}, could not be found";

        }
    }
}
