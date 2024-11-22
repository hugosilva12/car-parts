import React from "react";
import Menu from "../../components/menus/menu";
import Toolbar from "../../components/utils/toolbar";
import Footer from "../../components/utils/footer";
import api from "../../service/api";
import Store from "../store/component_store";
import Utils from "../../components/utils/Utils";
import Dashboard from "../dashboard/component_dashboard";
import CreateCar from "../cars/component_create_car";

class Home extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      component: <Store />,
    };
    this.changeComponent = this.changeComponent.bind(this);
    this.state.token = localStorage.getItem("token");
    if (this.state.token == "") {
      window.location.href = "/";
    }
    document.title = "Home";
  }
  componentDidMount() {
   /*  const response = api.get("/purchases/cars/", {
      headers: {
        Authorization: `Bearer ${this.state.token}`,
        "Content-Type": "application/json",
      },
    });

    response.then((result) => this.setState({ cars: result.data["content"] })); */
    const feature = Utils.prototype.checkFeature();
    console.log(feature)
    switch (feature) {
      case "ADMIN":
        this.changeComponent(<Dashboard />)
        break;
      case "WORKER":
        this.changeComponent(<CreateCar />)
        break;
    }
  }

  changeComponent = (component) => {
    this.setState({ ...this.state, component: component });
  };

  render() {
    return (
      <div className="container-xxl position-relative bg-white d-flex p-0">
        <Menu onChangeMenu={this.changeComponent} />
        <div className="content">
          <Toolbar />
          {this.state.component}
          <Footer />
        </div>
      </div>
    );
  }
}

export default Home;
