import { HighlightSettings } from "@syncfusion/ej2-react-maps";
import React from "react";
import "./navbar.css";
import Utils from "./Utils";

class ToolBar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      link: "/home",
      imageName: "img/client.png",
    };
  }

  componentDidMount() {
    const feature = Utils.prototype.checkFeature()
    switch (feature) {
      case "ADMIN":
        this.setState({...this.state, imageName: "img/admin.png"})
        break
      case "WORKER":
        this.setState({...this.state, imageName: "img/worker.png"})
        break
    }
  }

  //Logout App
  logOut = (e) => {
    localStorage.setItem("token", "");
    localStorage.setItem("id", "");
    localStorage.setItem("name", "");
    window.location.href = "/";
  };

  render() {
    return (
      <>
        <nav className="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
          <a href={this.state.link} className="navbar-brand d-flex d-lg-none me-4">
            <h2 className="text-primary mb-0">
              <i className="fa fa-hashtag" />
            </h2>
          </a>
          <div className="navbar-nav align-items-center ms-auto">
            <div className="nav-item dropdown">
              <a
                className="nav-link dropdown-toggle"
                data-bs-toggle="dropdown"
              >
                <img
                  className="rounded-circle me-lg-2"
                  src={this.state.imageName}
                  alt=""
                  style={{ width: "40px", height: "40px" }}
                />
                <span className="d-none d-lg-inline-flex">
                  {localStorage.getItem("name")}
                </span>
              </a>
              <div className="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                <a onClick={this.logOut} className="dropdown-item">
                  Log Out
                </a>
              </div>
            </div>
          </div>
        </nav>
      </>
    );
  }
}

export default ToolBar;
