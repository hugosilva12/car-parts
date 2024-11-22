import React from "react";
import api from "../../service/api";

class CreateCar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      brand: "",
      engine_capacity: 0,
      // entryDate: new Date(),
      fuelType: "DIESEL",
      gear: "MANUAL",
      hp: 0,
      km: 0,
      model: "",
      price: 0,
      vin: "",
      year: 0,
      error: ""
    };
  }

  handlePost = async (e) => {
    e.preventDefault();

    let isToPost = true;

    const {
      brand,
      engine_capacity,
      // entryDate,
      fuelType,
      gear,
      hp,
      km,
      model,
      price,
      vin,
      year,
    } = this.state;
    const dataToInsert = JSON.stringify({
      brand: brand,
      engine_capacity: engine_capacity,
      // entryDate: entryDate,
      fuelType: fuelType,
      gear: gear,
      hp: hp,
      km: km,
      model: model,
      price: price,
      vin: vin,
      year: year,
    });

    if (isToPost) {
      const response = await api.post("/purchases/cars", dataToInsert, {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("token")}`
        },
      });
      if (response.status == 200 || response.status == 201) {
        this.setState({ error: "Criado com sucesso!", showAlert: true });
      } else {
        this.setState({ error: "Campos Inválidos" });
      }
    }

    alert(`Criado com sucesso !`);
  };

  render() {
    return (
      <>
      <div className="container-fluid pt-4 px-4">
            <div
              className="col-sm-12 col-xl-12"
              style={{ textAlign: "center" }}
            >
              <h1>Adicionar carro</h1>
            </div>
            <div className="row g-4">
              <div className="col-sm-12 col-xl-12">
                <div className="bg-light rounded h-100 p-4">
                  <i className="fa fa-AiOutlinePlusCircle"></i>
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
                        onChange={(e) =>
                          this.setState({ price: e.target.value })
                        }
                      />
                    </div>
                    <div className="form-floating mb-3">
                      <select
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
                      Adicionar Carro
                    </button>{" "}
                    <br />
                    <h3 className="text-center mb-0">{this.state.error}</h3>
                  </form>
                </div>
              </div>
            </div>
          </div>
      </>
    );
  }
}

export default CreateCar;
