import React from 'react'
import api from '../../service/api'
import { formatValueWithRegex } from '../../global/utils'
import ChartPie from '../../components/charts/chart_pie'
import Chart from '../../components/charts/chart'
import { Pie } from 'react-chartjs-2';
class Dashboard extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      token: "",
      summaryUser: {},
      price: 0,
      summaryStorage: [],
      cars: []
    }
    this.state.token = localStorage.getItem("token");
    if (this.state.token == "") {
      window.location.href = '/';
    }
    document.title = "Home"
  }
  componentDidMount() {
    const response = api.get("/purchases/cars/", {
      headers: {
        Authorization: `Bearer ${this.state.token}`,
        'Content-Type': 'application/json'
      }
    });

    response.then(result => this.setState({ cars: result.data['content'] }));

    const responseHistory = api.get("/prices/itemprices", {
      headers: {
        Authorization: `Bearer ${this.state.token}`,
        'Content-Type': 'application/json'
      }
    });

    responseHistory.then(result => this.setState({ parts: result.data != 'Precarious Service is taking longer than expected. Please try again late' ? result.data : [] }));

    const responseStorage = api.get("/storage/sections", {
      headers: {
        Authorization: `Bearer ${this.state.token}`,
        'Content-Type': 'application/json'
      }
    });

    responseStorage.then(result => {
      console.log("Response", result)

      this.setState({ summaryStorage: result.data })
    }
    );




  }
  render() {
    let spend = 0
    let nrCars = 0
    if (this.state.cars != undefined) {
      this.state.cars.map(e => {
        spend = spend + e['price']
        nrCars++
      })
    }
    let median = spend / nrCars

    let totalWin = 0
    if (this.state.parts != undefined) {
      
      this.state.parts.map(e => {
        totalWin = totalWin + e['price']
      })
    }

    let nrAutoBody = -1
    let wheels = 0
    let nrBrakes = 0
    let nrExteriorAcessories = 0
    let nrInteriorAcessories = 0
    let nrEngine = 0
    let nrTools = 0


    let pAutoBody = 0
    let pwheels = 0
    let pBrakes = 0
    let pExteriorAcessories = 0
    let pInteriorAcessories = 0
    let pEngine = 0
    let pTools = 0

    let totalSales = 0
    if (this.state.parts != undefined) {
      nrAutoBody = 0
      this.state.parts.map(e => {
        if (e.category.id == 1) {
          nrAutoBody++;
          pAutoBody = pAutoBody + e['price']
        } else if (e.category.id == 8) {
          wheels++
          pwheels = pwheels + e['price']
        } else if (e.category.id == 3) {
          nrBrakes++
          pBrakes = pBrakes + e['price']
          console.log()
        } else if (e.category.id == 4) {
          nrExteriorAcessories++
          pExteriorAcessories = pExteriorAcessories + e['price']
        } else if (e.category.id == 6) {
          nrInteriorAcessories++
          pInteriorAcessories = pInteriorAcessories + e['price']
        } else if (e.category.id == 2) {
          nrEngine++
          pEngine = pEngine + e['price']
        } else if (e.category.id == 7) {
          nrTools++
          pTools = pTools + e['price']
        }
        totalSales++
      })
    }


    const chartData1 = {
      labels: ['Exterior Accessories', 'Interior Accessories', 'Engine & Drivetrain', 'Brakes, Suspension', 'Auto Body Parts', 'Wheels',
        'Tools and Garage'
      ],
      label: '',
      datasets: [
        {
          data: [
            pExteriorAcessories, pInteriorAcessories, pEngine, pBrakes, pAutoBody, pwheels, pTools
          ],
          backgroundColor: [
            'rgb(0, 156, 255)',
            'rgb(255, 165, 0)',
            'rgb(33, 188, 205)',
            'rgb(255, 0, 0)',
            'rgb(60, 179, 113)',
            'rgba(237, 201, 8, 0.8)',
            'rgba(86, 8, 237, 0.8)'
          ]
        }
      ]
    }

    const chartData2 = {
      labels: ['Exterior Accessories', 'Interior Accessories', 'Engine & Drivetrain', 'Brakes, Suspension', 'Auto Body Parts', 'Wheels',
        'Tools and Garage'
      ],
      datasets: [
        {
          data: [
            nrExteriorAcessories, nrInteriorAcessories, nrEngine, nrBrakes, nrAutoBody, wheels, nrTools
          ],
          backgroundColor: [
            'rgb(0, 156, 255)',
            'rgb(255, 165, 0)',
            'rgb(33, 188, 205)',
            'rgb(255, 0, 0)',
            'rgb(60, 179, 113)',
            'rgba(237, 201, 8, 0.8)',
            'rgba(86, 8, 237, 0.8)'
          ]
        }
      ]
    }


    let summaryStorage0 = 0
    let summaryStorage1 = 0
    let summaryStorage2 = 0
    let summaryStorage3 = 0
    let summaryStorage4 = 0
    let summaryStorage5 = 0
    let summaryStorage6 = 0
    let summaryStorage7 = 0

    let summaryStock0 = 0
    let summaryStock1 = 0
    let summaryStock2 = 0
    let summaryStock3 = 0
    let summaryStock4 = 0
    let summaryStock5 = 0
    let summaryStock6 = 0
    let summaryStock7 = 0

   
    if (this.state.summaryStorage.length != 0) {
      summaryStorage0 = this.state.summaryStorage[0]['freePositions'];
      summaryStorage1 = this.state.summaryStorage[1]['freePositions'];
      summaryStorage2 = this.state.summaryStorage[2]['freePositions'];
      summaryStorage3 = this.state.summaryStorage[3]['freePositions'];
      summaryStorage4 = this.state.summaryStorage[4]['freePositions'];
      summaryStorage5 = this.state.summaryStorage[5]['freePositions'];
      summaryStorage6 = this.state.summaryStorage[6]['freePositions'];
      summaryStorage7 = this.state.summaryStorage[7]['freePositions'];
      summaryStock0 = 25 - summaryStorage0;
      summaryStock1 = 25 - summaryStorage1;
      summaryStock2 = 25 - summaryStorage2;
      summaryStock3 = 25 - summaryStorage3;
      summaryStock4 = 25 - summaryStorage4;
      summaryStock5 = 25 - summaryStorage5;
      summaryStock6 = 25 - summaryStorage6;
      summaryStock7 = 25 - summaryStorage7;
    }


    const chartData3 = {
      labels: ['Secção 1', 'Secção 2', 'Secção 3', 'Secção 4', 'Secção 5', 'Secção 6', 'Secção 7', 'Secção 8',

      ],
      datasets: [
        {
          data: [
            summaryStorage0, summaryStorage1, summaryStorage2, summaryStorage3, summaryStorage4, summaryStorage5, summaryStorage6, summaryStorage7
          ],
          backgroundColor: [
            'rgb(0, 156, 255)',
            'rgb(255, 165, 0)',
            'rgb(33, 188, 205)',
            'rgb(255, 0, 0)',
            'rgb(60, 179, 113)',
            'rgba(237, 201, 8, 0.8)',
            'rgba(86, 8, 237, 0.8)'
          ]
        }
      ]
    }

    summaryStock0 = 25 - summaryStorage0;
    summaryStock1 = 25 - summaryStorage1;
    summaryStock2 = 25 - summaryStorage2;
    summaryStock3 = 25 - summaryStorage3;
    summaryStock4 = 25 - summaryStorage4;
    summaryStock5 = 25 - summaryStorage5;
    summaryStock6 = 25 - summaryStorage6;
    summaryStock7 = 25 - summaryStorage7;

    const chartData4 = {
      labels: ['Auto Body Parts & Mirrors','Brakes, Suspension & Steering', 'Exterior Accessories', 'Headlights & Lighting', 'Interior Accessories','Tools & Garage', 'Wheels',
      'Tools and Garage'
      ],
      datasets: [
        {
          data: [
            summaryStock0, summaryStock1, summaryStock2, summaryStock3, summaryStock4, summaryStock5, summaryStock6, summaryStock7
          ],
          backgroundColor: [
            'rgb(0, 156, 255)',
            'rgb(255, 165, 0)',
            'rgb(33, 188, 205)',
            'rgb(255, 0, 0)',
            'rgb(60, 179, 113)',
            'rgba(237, 201, 8, 0.8)',
            'rgba(86, 8, 237, 0.8)'
          ]
        }
      ]
    }

    return (
      <>
        <div className="container-fluid pt-4 px-4">
        <div class="col-sm-12 col-xl-12">
            <h1 style={{ textAlign: 'center' }}>Sumário</h1>
            </div>
          <div className="row g-4">
            <div className="col-sm-12 col-xl-12">
              <img src="img/truck3.png" alt="" style={{ width: '60%', display: "block", marginLeft: "auto", marginRight: "auto" }} />
            </div>
          </div>
        </div>
        <div className="container-fluid pt-4 px-4">
          <div className="row g-4">
            <div class="col-sm-12 col-xl-6">
              {nrAutoBody != -1 &&
                <>
                  <h4 style={{ textAlign: 'center' }}> Número de vendas por categoria</h4>
                  <ChartPie chartData={chartData2} legendPosition="top" />
                </>
              }

            </div>
            <div class="col-sm-12 col-xl-6">
              {nrAutoBody != -1 &&
                <>
                  <h4 style={{ textAlign: 'center' }}> Valor faturado por categoria</h4>
                  <Chart chartData={chartData1} legendPosition="bottom" />
                </>
              }
            </div>
            <div className="col-sm-6 col-xl-4">
              <div className="bg-light rounded d-flex align-items-center justify-content-between p-4">
                <i className="fa fa-arrow-down fa-3x text-primary" />
                <div className="ms-3">
                  <p className="mb-2" style={{ textAlign: " center", fontSize: "18px" }}>Total gasto em carros</p>
                  <h6 className="mb-0" style={{ textAlign: " center", fontSize: "20px" }}>{formatValueWithRegex(spend)} €</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-xl-4">
              <div className="bg-light rounded d-flex align-items-center justify-content-between p-4">
                <i className="fa fa-arrow-up fa-3x text-primary" />
                <div className="ms-3">
                  <p className="mb-2" style={{ textAlign: " center", fontSize: "18px" }}>Total ganho em peças</p>
                  <h6 className="mb-0" style={{ textAlign: " center", fontSize: "20px" }}>{formatValueWithRegex(totalWin)} €</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-xl-4">
              <div className="bg-light rounded d-flex align-items-center justify-content-between p-4">
                <i className="fa fa-chart-bar fa-3x text-primary" />
                <div className="ms-3">
                  <p className="mb-2" style={{ textAlign: " center", fontSize: "18px" }}>Margem de lucro</p>
                  <h6 className="mb-0" style={{ textAlign: " center", fontSize: "20px" }}>{formatValueWithRegex(totalWin - spend)} €</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-xl-4">
              <div className="bg-light rounded d-flex align-items-center justify-content-between p-4">
                <i className="fa fa-car fa-3x text-primary" />
                <div className="ms-3">
                  <p className="mb-2" style={{ textAlign: " center", fontSize: "18px" }}>Preço médio carro</p>
                  <h6 className="mb-0" style={{ textAlign: " center", fontSize: "20px" }}>{formatValueWithRegex(median)} €</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-xl-4">
              <div className="bg-light rounded d-flex align-items-center justify-content-between p-4">
                <i className="fa fa-briefcase fa-3x text-primary" />
                <div className="ms-3">
                  <p className="mb-2" style={{ textAlign: " center", fontSize: "18px" }}>Total de vendas </p>
                  <h6 className="mb-0" style={{ textAlign: " center", fontSize: "20px" }}>{totalSales}</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-xl-4">
              <div className="bg-light rounded d-flex align-items-center justify-content-between p-4">
                <i className="fa fa-briefcase fa-3x text-primary" />
                <div className="ms-3">
                  <p className="mb-2" style={{ textAlign: " center", fontSize: "18px" }}>Total de compras </p>
                  <h6 className="mb-0" style={{ textAlign: " center", fontSize: "20px" }}>{nrCars}</h6>
                </div>
              </div>
            </div>
            <div class="col-sm-12 col-xl-12">
            <h1 style={{ textAlign: 'center' }}>Gestão de Armazém</h1>
            </div>
            <div class="col-sm-12 col-xl-6">
              {summaryStorage7 != 0 &&
                <>
                  <h4 style={{ textAlign: 'center' }}>Armazenamento disponível</h4>
                  <Chart chartData={chartData3} legendPosition="bottom" />
                </>
              }
            </div>
            <div class="col-sm-12 col-xl-6" style={{ height: 400 }}>
              {summaryStorage7 != 0 &&
                <>
                  <h4 style={{ textAlign: 'center' }}>Peças em stock</h4>
                  <ChartPie chartData={chartData4} legendPosition="top" />
                </>
              }
            </div>
          </div>
        </div>
      </>
    )
  }
}


export default Dashboard
