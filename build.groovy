node("linux") {
    customImage = ""
    stage("build docker") {
        customImage = docker.build("dvdrbn/opsschool-jenkins-test-01")
    }
    stage("verify dockers") {
        sh "docker images"
    }
}
