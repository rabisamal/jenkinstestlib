@Library('my-libs')_
pipeline {
    agent any
	
    stages {
        stage("Build") {
            steps {
		env.IMAGENAME = mytest()
		    sh "${IMAGENAME}"
            }
        }
    }
 }
