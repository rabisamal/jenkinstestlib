@Library('my-libs')_
pipeline {
    agent any
	
    stages {
        stage("Build") {
            steps {
		IMAGENAME = mytest()
		sh "${IMAGENAME}"
            }
        }
    }
 }
