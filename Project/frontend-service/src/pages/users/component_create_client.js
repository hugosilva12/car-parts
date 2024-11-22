import React from 'react';
import api from '../../service/api';


class CreateClient extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      value: 0,
      username: "",
      password: "",
      first_name: "",
      profile: "",
      error: "",
      last_name: "",
      email: "",
      key:0
    }
  }

  //Select
  handleChangeProfile = (e) => {
    this.setState({ profile: e.target.value });
  }

  cleanForm = (e) => {
    this.setState({ key: this.state.key + 1 });
  }

  handlePost = async e => {
    e.preventDefault();
    
    let isToPost = true
    const { username, password, first_name, last_name, email, profile } = this.state;

    if (profile == "") {
      this.setState({ error: "Atribua Perfil!" });
      isToPost = false
    }

    if (password == "") {
      this.setState({ error: "Coloque a password!" });
      isToPost = false;
    }

    if (email == "") {
      this.setState({ error: "Atribua um email" });
      isToPost = false;
    }


   const dataToInsert = JSON.stringify({
      username: username,
      password: password,
      firstName: first_name,
      lastName: last_name,
      typeClient: profile,
      email: email
    })

    if (isToPost) {
      const response = await api.post("/users/clients", dataToInsert, {
        headers: {
          'Content-Type': 'application/json'
        }
      });
      if (response.status == 200 || response.status == 201) {
        this.setState({ error: "Criado com sucesso!", showAlert: true });

      } else {
        this.setState({ error: "Campos Inválidos" });
      }
    }

    alert(`Criado com sucesso !`);
  
  }

  render() {

    return (
      <>
      <div className="container-fluid pt-4 px-4">
            <div className="col-sm-12 col-xl-12" style={{ textAlign: 'center' }}>
              <h1 >Criar cliente</h1>
            </div>
            <div className="row g-4">
              <div className="col-sm-12 col-xl-12">

                <div className="bg-light rounded h-100 p-4">
                  <img src="img/add-user.png" style={{ height: 50 }} />
                  <a href="/" style={{ fontSize: '18px' , float:'right' }}> <i class="fas fa-home"> Login</i> </a>
                  <form>
                    <div key={this.state.key}  className="mb-3">
                      <label htmlFor="exampleInputEmail1" className="form-label" >Username</label>
                      <input type="text" className="form-control" required id="text" onChange={e => this.setState({ username: e.target.value })} />
                    </div>
                    <div  key={this.state.key} className="mb-3">
                      <label htmlFor="exampleInputEmail1" className="form-label" >Email</label>
                      <input type="email" className="form-control" required id="text" onChange={e => this.setState({ email: e.target.value })} />
                    </div>
                    <div  key={this.state.key} className="mb-3">
                      <label htmlFor="exampleInputEmail1" className="form-label" >Nome</label>
                      <input type="text" className="form-control" required id="text" onChange={e => this.setState({ first_name: e.target.value })} />
                    </div>
                    <div  key={this.state.key} className="mb-3">
                      <label htmlFor="exampleInputEmail1" className="form-label" >Apelido</label>
                      <input type="text" className="form-control" required id="text" onChange={e => this.setState({ last_name: e.target.value })} />
                    </div>
                    <div  key={this.state.key} className="mb-3">
                      <label htmlFor="exampleInputPassword1" className="form-label" >Password</label>
                      <input type="password" required className="form-control" id="exampleInputPassword1" onChange={e => this.setState({ password: e.target.value })} />
                    </div>
                    <div  key={this.state.key} className="form-floating mb-3">
                      <select onChange={this.handleChangeProfile} className="form-select" id="floatingSelect" aria-label="Floating label select example">
                      <option value={''}></option>
                        <option value={'PARTICULAR'}>Particular</option>
                        <option value={'PROFESSIONAL'}>Professional</option>
                        <option value={'EMPRESARIAL'}>Empresarial</option> 
                      </select>
                      <label htmlFor="floatingSelect">Tipo de  cliente </label>
                    </div>
                    <button className="btn btn-primary" onClick={this.handlePost} style={{ width: 140 }}>Criar User</button> <br/>
                   
                    <h3 class="text-center mb-0">{this.state.error}</h3>
                  </form>
                </div>
              </div>

            </div>
          </div>
      </>
    )
  }
}


export default CreateClient

