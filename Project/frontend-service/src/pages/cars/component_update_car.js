import React from 'react';


import api from '../../service/api';


class UpdateCar extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      id: props.car.id,
      brand: props.car.brand,
      engine_capacity: props.car.engine_capacity,
      // entryDate: new Date(),
      fuelType: props.car.fuelType,
      gear: props.car.gear,
      hp: props.car.hp,
      km: props.car.km,
      model: props.car.model,
      price: props.car.price,
      vin: props.car.vin,
      year: props.car.year,
      error: ""
    };
    this.state.token = localStorage.getItem("token");

    if (this.state.token == "") {
      window.location.href = '/';
    }
    document.title = "Atualizar carro"
  }
  //Selects
  handleChangeProfile = (e) => {
    this.setState({ profile: e.target.value });
  }

  handlePost = async e => {
    this.setState({ error: "" });
    e.preventDefault();
    let isToPost = true
    const { brand,
      engine_capacity,
      fuelType,
      gear,
      hp,
      km,
      model,
      price,
      vin,
      year } = this.state;

    if (isToPost) {
      const response = await api.put("/purchases/cars/" + this.state.id, JSON.stringify({ brand,
        engine_capacity,
        fuelType,
        gear,
        hp,
        km,
        model,
        price,
        vin,
        year }), {
        headers: {
          Authorization: `Bearer ${this.state.token}`, 'Accept': 'application/json',
          'Content-Type': 'application/json'
        }
      });


      if (response.status == 200) {
        this.setState({ error: "Atualizado com sucesso!" });

      }
    }
  }

  render() {

    return (
      <>
        <div className="container-fluid pt-4 px-4">
          <div
            className="col-sm-12 col-xl-12"
            style={{ textAlign: "center" }}
          >
            <h1>Editar Carro</h1>
          </div>
          <div className="row g-4">
            <div className="col-sm-12 col-xl-12">
              <div className="bg-light rounded h-100 p-4">
                <img src="/img/organization.png" style={{ height: 50 }} />
                <form>
                  <div className="mb-3">
                    <label
                      htmlFor="exampleInputEmail1"
                      className="form-label"
                    >
                      Marca
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      required
                      value={this.state.brand}
                      onChange={(e) =>
                        this.setState({ brand: e.target.value })
                      }
                    />
                  </div>
                  <div className="mb-3">
                    <label
                      htmlFor="exampleInputEmail1"
                      className="form-label"
                    >
                      Modelo
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      required
                      value={this.state.model}
                      onChange={(e) =>
                        this.setState({ model: e.target.value })
                      }
                    />
                  </div>
                  <div className="mb-3">
                    <label
                      htmlFor="exampleInputEmail1"
                      className="form-label"
                    >
                      Ano
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      required
                      defaultValue="0"
                      min="1800"
                      max="2023"
                      value={this.state.year}
                      onChange={(e) =>
                        this.setState({ year: e.target.value })
                      }
                    />
                  </div>
                  <div className="mb-3">
                    <label
                      htmlFor="exampleInputEmail1"
                      className="form-label"
                    >
                      Capacidade do motor
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      required
                      defaultValue="0"
                      min="900"
                      max="10000"
                      value={this.state.engine_capacity}
                      onChange={(e) =>
                        this.setState({ engine_capacity: e.target.value })
                      }
                    />
                  </div>
                  {/* <div className="mb-3">
                      <label
                        htmlFor="exampleInputEmail1"
                        className="form-label"
                      >
                        Data de entrada
                      </label>
                      <input
                        type="datetime-local"
                        className="form-control"
                        required
                        onChange={(e) =>
                          this.setState({ entryDate: e.target.value })
                        }
                      /> 
                    </div>*/}
                  <div className="mb-3">
                    <label
                      htmlFor="exampleInputEmail1"
                      className="form-label"
                    >
                      Kilometragem
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      required
                      defaultValue="0"
                      min="0"
                      max="999999999"
                      value={this.state.km}
                      onChange={(e) => this.setState({ km: e.target.value })}
                    />
                  </div>
                  <div className="mb-3">
                    <label
                      htmlFor="exampleInputEmail1"
                      className="form-label"
                    >
                      Cavalagem
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      required
                      defaultValue="0"
                      min="50"
                      max="2000"
                      value={this.state.hp}
                      onChange={(e) => this.setState({ hp: e.target.value })}
                    />
                  </div>
                  <div className="mb-3">
                    <label
                      htmlFor="exampleInputEmail1"
                      className="form-label"
                    >
                      Vin
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      required
                      value={this.state.vin}
                      onChange={(e) => this.setState({ vin: e.target.value })}
                    />
                  </div>
                  <div className="mb-3">
                    <label
                      htmlFor="exampleInputEmail1"
                      className="form-label"
                    >
                      Preço
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      required
                      defaultValue="0"
                      min="0"
                      max="9999999999999"
                      value={this.state.price}
                      onChange={(e) =>
                        this.setState({ price: e.target.value })
                      }
                    />
                  </div>
                  <div className="form-floating mb-3">
                    <select
                      value={this.state.gear}
                      onChange={(e) =>
                        this.setState({ gear: e.target.value })
                      }
                      className="form-select"
                      id="floatingSelect"
                      aria-label="Floating label select example"
                    >
                      <option value={"MANUAL"}>Manual</option>
                      <option value={"AUTOMATIC"}>Automática</option>
                    </select>
                    <label htmlFor="floatingSelect">Caixa </label>
                  </div>
                  <div className="form-floating mb-3">
                    <select
                      value={this.state.fuelType}
                      onChange={(e) =>
                        this.setState({ fuelType: e.target.value })
                      }
                      className="form-select"
                      id="floatingSelect"
                      aria-label="Floating label select example"
                    >
                      <option value={"DIESEL"}>Gasóleo</option>
                      <option value={"PETROL"}>Gasolina</option>
                      <option value={"ELETRIC_AND_PETROL"}>
                        Hibrido (Gasolina e elétrico)
                      </option>
                      <option value={"ELETRIC"}>Elétrico</option>
                    </select>
                    <label htmlFor="floatingSelect">Combustível </label>
                  </div>
                  <button
                    className="btn btn-primary"
                    onClick={this.handlePost}
                    style={{ width: 140 }}
                  >
                    Atualizar Carro
                  </button>{" "}
                  <br />
                  <h3 className="text-center mb-0">{this.state.error}</h3>
                </form>
              </div>
            </div>
          </div>
        </div>
      </>
    )
  }
}


export default UpdateCar

