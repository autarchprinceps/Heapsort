pipeline {
  agent {
    node {
      label 'Cloud-Ubuntu-Slave'
    }
    
  }
  stages {
    stage('A') {
      steps {
        parallel(
          "A": {
            echo 'A'
            input(message: 'Hallo', id: 'H', ok: 'String')
            
          },
          "B": {
            echo 'B'
            
          }
        )
      }
    }
  }
}