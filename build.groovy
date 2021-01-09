node("linux") {
    customImage = ""
    stage("clone repo"){
        checkout scm
    }
    stage("build docker") {
        customImage = docker.build("dvdrbn/opsschool-jenkins-test-01")
    }
    stage("verify dockers") {
        sh "docker images"
    }
}
