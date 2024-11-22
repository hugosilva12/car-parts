import React from "react";
import Dashboard from "../../pages/dashboard/component_dashboard";
import Store from "../../pages/store/component_store";
import Utils from "../utils/Utils";
import ListClients from "../../pages/users/component_list_client";
import ListEmployees from "../../pages/users/component_list_employees";
import ListCars from "../../pages/cars/component_list_cars";
import ListParts from "../../pages/parts/component_list_parts";
import CreateCar from "../../pages/cars/component_create_car";
import CreatePart from "../../pages/parts/component_create_part";
import AddUser from "../../pages/users/component_create_employee";
import ListHistoryParts from "../../pages/parts/component_list_history_price";
import Purchases from "../../pages/store/component_purchases"


class Menu extends React.Component {
  constructor(props) {
    super(props);
    this.changeComponent = props.onChangeMenu.bind(this);
    this.state = {
      title: "Client",
      feature: "client",
      imageName: "img/client.png",
    };
  }

  componentDidMount() {
    const feature = Utils.prototype.checkFeature();
    switch (feature) {
      case "ADMIN":
        this.setState({
          ...this.state,
          imageName: "img/admin.png",
          feature: "admin",
          title: "Admin",
        });
        break;
      case "WORKER":
        this.setState({
          ...this.state,
          imageName: "img/worker.png",
          feature: "worker",
          title: "Worker",
        });
        break;
    }
  }

  render() {
    return (
      <div className="sidebar pe-4 pb-3">
        <nav className="navbar bg-light navbar-light">
          <a href="/home" className="navbar-brand mx-4 mb-3">
            <h3 className="text-primary">
              <i className="fa fa-hashtag me-2" />
              {this.state.title}
            </h3>
          </a>
          <div className="d-flex align-items-center ms-4 mb-4">
            <div className="position-relative">
              <img
                className="rounded-circle"
                src={this.state.imageName}
                alt=""
                style={{ width: "40px", height: "40px" }}
              />
              <div className="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1" />
            </div>
            <div className="ms-3">
              <h6 className="mb-0" style={{ fontSize: "20px" }}>
                {localStorage.getItem("username")}
              </h6>
              <span>{this.state.feature}</span>
            </div>
          </div>

          <div className="navbar-nav w-100">
            {this.state.feature == "admin" && (
              <div className="nav-item">
                {" "}
                <a
                  onClick={() => {
                    this.changeComponent(<Dashboard />);
                  }}
                  className="nav-link dropdown-toggle"
                  style={{ fontSize: "20px" }}
                >
                  <i className="fa fa-signal me-2" /> Dashboard
                </a>
              </div>
            )}

            {this.state.feature == "admin" && (
              <div className="nav-item">
                {" "}
                <a
                  onClick={() => {
                    this.changeComponent(<ListHistoryParts />);
                  }}
                  className="nav-link dropdown-toggle"
                  style={{ fontSize: "20px" }}
                >
                  <i className="fa fa-book me-2" /> Histórico Vendas
                </a>
              </div>
            )}

            {this.state.feature == "client" && (
              <>
                <div className="nav-item">
                  {" "}
                  <a
                    onClick={() => {
                      this.changeComponent(<Store />);
                    }}
                    className="nav-link dropdown-toggle"
                    style={{ fontSize: "20px" }}
                  >
                    <i className="fa fa-shopping-cart me-2" /> Loja
                  </a>
                </div>
                <div className="nav-item">
                  {" "}
                  <a
                    onClick={() => {
                      this.changeComponent(<Purchases />);
                    }}
                    className="nav-link dropdown-toggle"
                    style={{ fontSize: "20px" }}
                  >
                    <i className="fa fa-book me-2" /> Histórico Compras
                  </a>
                </div>
              </>)}

            {this.state.feature == "admin" && (
              <>
                <div className="nav-item dropdown">
                  <a
                    href="#"
                    className="nav-link dropdown-toggle"
                    data-bs-toggle="dropdown"
                    style={{ fontSize: "20px" }}
                  >
                    <i className="fa fa-users me-2" /> Utilizadores
                  </a>
                  <div className="dropdown-menu bg-transparent border-0">
                    <a
                      onClick={() => {
                        this.changeComponent(<ListClients onEdit={this.changeComponent} />);
                      }}
                      className="dropdown-item"
                      style={{ fontSize: "18px" }}
                    >
                      <i class="fa fa-credit-card"> Visualizar Clientes </i>
                    </a>
                    <a
                      onClick={() => {
                        this.changeComponent(<ListEmployees onEdit={this.changeComponent} />);
                      }}
                      className="dropdown-item"
                      style={{ fontSize: "18px" }}
                    >
                      {" "}
                      <i class="fa fa-address-card">
                        {" "}
                        Visualizar Funcionários{" "}
                      </i>
                    </a>
                    <a
                      onClick={() => {
                        this.changeComponent(<AddUser />);
                      }}
                      className="dropdown-item"
                      style={{ fontSize: "18px" }}
                    >
                      {" "}
                      <i class="fas fa-edit"> Adicionar Utilizadores</i>{" "}
                    </a>
                  </div>
                </div>
              </>
            )}

            {this.state.feature != "client" && (
              <>
                <div className="nav-item dropdown">
                  <a
                    href="#"
                    className="nav-link dropdown-toggle"
                    data-bs-toggle="dropdown"
                    style={{ fontSize: "20px" }}
                  >
                    <i className="fa fa-car me-2" /> Carros
                  </a>
                  <div className="dropdown-menu bg-transparent border-0">
                    <a
                      onClick={() => {
                        this.changeComponent(<ListCars onEdit={this.changeComponent} />);
                      }}
                      className="dropdown-item"
                      style={{ fontSize: "18px" }}
                    >
                      {" "}
                      <i class="fa fa-list"> Visualizar Carros </i>
                    </a>
                    <a
                      onClick={() => {
                        this.changeComponent(<CreateCar />);
                      }}
                      className="dropdown-item"
                      style={{ fontSize: "18px" }}
                    >
                      {" "}
                      <i class="fas fa-edit"> Adicionar Carro</i>{" "}
                    </a>
                  </div>
                </div>
                <div className="nav-item dropdown">
                  <a
                    href="#"
                    className="nav-link dropdown-toggle"
                    data-bs-toggle="dropdown"
                    style={{ fontSize: "20px" }}
                  >
                    <i className="fa fa-cogs me-2" /> Peças
                  </a>
                  <div className="dropdown-menu bg-transparent border-0">
                    <a
                      onClick={() => {
                        this.changeComponent(<ListParts />);
                      }}
                      className="dropdown-item"
                      style={{ fontSize: "18px" }}
                    >
                      {" "}
                      <i class="fa fa-list"> Visualizar Peças </i>
                    </a>
                    <a
                      onClick={() => {
                        this.changeComponent(<CreatePart />);
                      }}
                      className="dropdown-item"
                      style={{ fontSize: "18px" }}
                    >
                      {" "}
                      <i class="fas fa-edit"> Adicionar Peça</i>{" "}
                    </a>
                  </div>
                </div>
              </>
            )}
          </div>
        </nav>
      </div>
    );
  }
}

export default Menu;
