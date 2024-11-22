import { useState } from 'react';
import api from '../../service/api';


function Login() {

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleUsername = (value) => {
    setUsername(value);
  };
  
  const handlePassword = (value) => {
    setPassword(value);
  };

  const handleSignIn = async e => {

    if(username == "" || password == ""){
      alert(`Insira dados válidos`);
      window.location.reload(false);
    }else{
      const form = new FormData()
      form.append("username",username)
      form.append("password",password)

    const response = api.post(`auth/login`, form).then((result) => {
      console.log("Result ", result.data['accessToken'])
      localStorage.setItem("username",username)
      localStorage.setItem("token",result.data['accessToken'])
      localStorage.setItem("feature", result.data['feature'] )
      localStorage.setItem("userAccountId", result.data['userAccountId'])

      window.location.href = '/home';
      
    }).catch((error)=>{
      if(error['response']['status'] == 401 ){
        setError('Dados invalidos ou user removido!' );
      }else{
        setError('Erro ao fazer login!' );
      }
  
     
    })

    console.log("Response  " , response['acessToken'])
  }
}

  return (
    <div className="container-xxl position-relative bg-white d-flex p-0">
        <div className="container-fluid">
          <div className="row h-100 align-items-center justify-content-center" style={{ minHeight: '100vh' }}>
            <div className="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
              <div className="bg-light rounded p-4 p-sm-5 my-4 mx-3">
                <div className="d-flex align-items-center justify-content-between mb-3">
                  <a href="index.html" className>
                    <h3 className="text-primary"><i className="fa fa-hashtag me-2" />MTSDS</h3>
                  </a>
              
                </div>
                <div className="form-floating mb-3">
                  <input type="email" className="form-control" id="floatingInput" placeholder="name@example.com" onChange={e => handleUsername(e.target.value)} />
                  <label htmlFor="floatingInput">Username</label>
                </div>
                <div className="form-floating mb-4">
                  <input type="password" className="form-control" id="floatingPassword" placeholder="Password" onChange={e => handlePassword(e.target.value)} />
                  <label htmlFor="floatingPassword">Password</label>
                </div>

                <button type="submit" className="btn btn-primary py-3 w-100 mb-4" onClick={handleSignIn}>Login</button>
                <p class="text-center mb-0">{error}</p>
                <a href="/register-client" style={{ fontSize: '18px' , textAlign:'right' }}> <i class="fas fa-edit">  Registar</i> </a>
              </div>
            </div>
          </div>
        </div>
  
      </div>

  );
}

export default Login;

