pipeline{
  agent any
  tools{
      maven "Maven"
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
          bat "mvn clean"
        }
      }
       stage('Unit Testing')
      {
        steps
        {
          bat "mvn test"
        }
      }
      stage('Sonar Analysis')
      {
        steps
        {
          withSonarQubeEnv("Test_Sonar")
          {
            bat "mvn sonar:sonar"
          }
        }
      }
	  stage('Upload to Artifactory')
	  {
		steps
		{
			rtMavenDeployer 
			{
				id: 'deployer',
				serverId: '123456789@artifactory',
				releaseRepo: 'akash.nagp2022',
				snapshotRepo: 'akash.nagp2022'
			}
			rtMavenRun 
			{
				pom: 'pom.xml',
				goals: 'clean install',
				deployerId: 'deployer'
			}
			rtPublishBuildInfo 
			{
				serverId: '123456789@artifactory'
		}	}
	  }
  }
}

