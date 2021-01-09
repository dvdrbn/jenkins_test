node("linux") {
    customImage = ""
    stage("create dockerfile") {
        sh """
            tee Dockerfile2 <<-'EOF'
              FROM ubuntu:latest
              RUN touch file-01.txt
EOF
        """
    }
    stage("build docker") {
        customImage = docker.build("dvdrbn/opsschool-jenkins-test-01")
    }
    stage("verify dockers") {
        sh "docker images"
    }
}
