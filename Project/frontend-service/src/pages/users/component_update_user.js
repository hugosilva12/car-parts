import React from "react";

import api from "../../service/api";

class UpdateUser extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: props.user.id,
      isEmployee:props.isEmployee,
      token: "",
      first_name: props.user.firstName,
      last_name: props.user.lastName,
      error: "",
      email: props.user.email,
      userState: props.user.userState,
    };
    this.state.token = localStorage.getItem("token");

    if (this.state.token == "") {
      window.location.href = "/";
    }
    
    console.log("Props ", props.user)
  
    document.title = "Atualizar user";
  }
  //Selects
  handleChangeProfile = (e) => {
    this.setState({ profile: e.target.value });
  };

  handleChangeOrganization = (e) => {
    console.log("Organization ", e.target.value);
    this.setState({ organization_id: e.target.value });
  };

  handlePost = async (e) => {
    this.setState({ error: "" });
    e.preventDefault();
    let isToPost = true;
    const { first_name, last_name, userState, email } = this.state;

    if (first_name == "") {
      this.setState({ error: "Atribua nome ao  User!" });
      isToPost = false;
    }
    if (email == -1) {
      this.setState({ error: "Atribua um email" });
      isToPost = false;
    }

    if (isToPost) {
      const path = this.state.isEmployee ? "/users/employees/" :"/users/clients/"

      const response = await api.put(
        path + this.state.id,
        JSON.stringify({
          firstName: first_name,
          lastName: last_name,
          email: email,
          userState: userState,
        }),
        {
          headers: {
            Authorization: `Bearer ${this.state.token}`,
            Accept: "application/json",
            "Content-Type": "application/json",
          },
        }
      );

      if (response.status == 200) {
        this.setState({ error: "Atualizado com sucesso!" });
      }
    }
  };

  render() {
    return (
      <>
        <div className="container-fluid pt-4 px-4">
          <div className="col-sm-12 col-xl-12" style={{ textAlign: "center" }}>
            <h1>Editar User</h1>
          </div>
          <div className="row g-4">
            <div className="col-sm-12 col-xl-12">
              <div className="bg-light rounded h-100 p-4">
                <img src="/img/organization.png" style={{ height: 50 }} />
                <form>
                  <div className="mb-3">
                    <label htmlFor="exampleInputEmail1" className="form-label">
                      Nome
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      required
                      id="text"
                      value={this.state.first_name}
                      onChange={(e) =>
                        this.setState({ first_name: e.target.value })
                      }
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="exampleInputEmail1" className="form-label">
                      Apelido
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      required
                      id="text"
                      value={this.state.last_name}
                      onChange={(e) =>
                        this.setState({ last_name: e.target.value })
                      }
                    />
                  </div>

                  <div className="mb-3">
                    <label
                      htmlFor="exampleInputPassword1"
                      className="form-label"
                    >
                      Email
                    </label>
                    <input
                      type="text"
                      required
                      className="form-control"
                      id="exampleInputPassword1"
                      value={this.state.email}
                      onChange={(e) => this.setState({ email: e.target.value })}
                    />
                  </div>
                  <button
                    className="btn btn-primary"
                    onClick={this.handlePost}
                    style={{ width: 200 }}
                  >
                    Atualizar User
                  </button>
                  <h3 class="text-center mb-0">{this.state.error}</h3>
                </form>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  }
}

export default UpdateUser;
