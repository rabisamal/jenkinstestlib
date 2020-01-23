@Library('my-libs')_
pipeline {
    agent any
	
    stages {
        stage("Build") {
            steps {
		mytest()
		sh "echo ${IMAGENAME}"
            }
        }
    }
 }
