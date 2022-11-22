pipeline{
  agent any
  tools{
      maven "apache-maven-3.8.6"
  }
  stages{
      stage('checkout')
      {
        steps
        {
          checkout scm
        }
      }
       stage('Build')
      {
        steps
        {
          sh "mvn install"
        }
      }
       stage('Unit Testing')
      {
        steps
        {
          sh "mvn test"
        }
      }
       stage('Sonar Analysis')
      {
        steps
        {
          withSonarQubeEnv("Test _Sonar")
          {
            sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar"
          }
        }
      }
  }
}
