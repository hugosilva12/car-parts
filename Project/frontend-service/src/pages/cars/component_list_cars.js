import React from 'react';
import api from '../../service/api';
import UpdateCar from './component_update_car'
import CarSummary from './component_car_summary'
import { formatValueWithRegex } from '../../global/utils'
class ListCars extends React.Component {

    constructor(props) {
        super(props)
        this.onEdit = props.onEdit;
        this.state = {
            cars: [],
            token: "",
        }
        this.state.token = localStorage.getItem("token");
        document.title = "Carros"
    }

    refuse(id) {
        api.delete("/purchases/cars/" + id, {
            headers: { Authorization: `Bearer ${this.state.token}` }

        }).then(alert("Removido com sucesso"));
    }

    componentDidMount() {
        const response = api.get("/purchases/cars/", {
            headers: { Authorization: `Bearer ${this.state.token}`,
            'Content-Type': 'application/json' }
        });
       
        response.then(result =>  this.setState({ cars: result.data['content'] != undefined ? result.data['content'] : []}) );
    }

     capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }

    render() {
        return (
            <>
                <div class="container-fluid pt-4 px-4">
                        <div class="row g-4">
                            <div class="col-sm-12 col-xl-12">
                            <div className="col-sm-12 col-xl-12" style={{ textAlign: 'center' }}>
                                        <h1 >Carros</h1>
                                    </div>
                                <div class="bg-light rounded h-100 p-4">
                                    <h6 class="mb-4">Carros</h6>
                                    <table class="table">
                                        <thead>
                                            <tr>
                                           
                                                <th scope="col"style={{ textAlign: 'center', fontSize: 20 }}>Marca </th>
                                                <th scope="col"style={{ textAlign: 'center', fontSize: 20 }}>Modelo </th>
                                                <th scope ="col"style={{ textAlign: 'center', fontSize: 20 }}>Ano </th>
                                                <th scope ="col"style={{ textAlign: 'center', fontSize: 20 }}>Combustível </th>
                                                <th scope ="col"style={{ textAlign: 'center', fontSize: 20 }}>Tipo de caixa</th>
                                                <th scope ="col"style={{ textAlign: 'center', fontSize: 20 }}>Preço</th>
                                                <th scope ="col"style={{ textAlign: 'center', fontSize: 20 }}>Valor gerado</th>
                                                <th scope="col"style={{ textAlign: 'center' , fontSize: 20 }}>Editar</th> 
                                                <th scope ="col"style={{ textAlign: 'center', fontSize: 20 }}>Ação</th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {this.state.cars.map((car, index) => (
                                                <>
                                                    <tr>
                                                        <td style={{ textAlign: 'center' }}>{car.brand}</td>
                                                        <td style={{ textAlign: 'center' }}>{car.model}</td> 
                                                        <td style={{ textAlign: 'center' }}>{car.year}</td> 
                                                        <td style={{ textAlign: 'center' }}>{this.capitalizeFirstLetter(car.fuelType.toLowerCase())}</td> 
                                                        <td style={{ textAlign: 'center' }}>{this.capitalizeFirstLetter(car.gear.toLowerCase())}</td>
                                                        <td style={{ textAlign: 'center' }}>{formatValueWithRegex(car.price)}€</td>
                                                        <td style={{ textAlign: 'center' }}><CarSummary id={car.id} /></td>
                                                        <td style={{ textAlign: 'center' }}><button type="button" onClick={() => this.onEdit(<UpdateCar car={car}/>)} class="btn btn-warning rounded-pill m-2">Editar</button></td> 
                                                        <td style={{ textAlign: 'center' }}><button type="button" onClick={() => this.refuse(car.id)} class="btn btn-danger rounded-pill m-2">Eliminar</button></td>
                                                        
                                                        </tr>
                                                </>
                                            ))}
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
                    </div>
            </>
        )
    }
}


export default ListCars

