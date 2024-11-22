import React from 'react';
import api from '../../service/api';
import { formatValueWithRegex } from '../../global/utils'
class Purchases extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            parts: [],
            token: "",
            showParts: true,
            showInterior: false,
            showWheels: false,
            showEngine: false,
            totalCost: 0
        }
        this.state.token = localStorage.getItem("token");
        document.title = "Histórico de vendas"
    }

    formatDate(date) {
        return date.toString().split("T")[0]
    }

    onEdit() {
        this.setState({ showParts: !this.state.showParts })
    }


    formatDescription(des) {
        if (des.hasOwnProperty('descrição')) {
            return des['descrição']
        }
        if (des.hasOwnProperty('cor')) {
            return des['cor']
        }
        return des['descrição']
    }


    formatDimension(des) {
        if (des.hasOwnProperty('dimensão')) {
            return des['dimensão']
        }
        return ''
    }

    formatBrand(des) {
        if (des.hasOwnProperty('marca')) {
            return des['marca']
        }
        return ''
    }


    formatView(e) {
        if (e.target.value == 'wheels') {
            this.setState({ showParts: false, showWheels: true })
        }

        if (e.target.value == 'parts') {
            this.setState({ showParts: true, showWheels: false, })
        }


    }

    sum(value) {
        if (value.state == 'CONFIRMED') {
            this.state.totalCost += value.price;
        }

    }


    capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }

    formatDate(date) {

        return date.toString().split("T")[0]
    }

    componentDidMount() {
        const response = api.get(`/sales/sale?search=client:${localStorage.getItem("userAccountId")}`, {
            headers: {
                Authorization: `Bearer ${this.state.token}`,
                'Content-Type': 'application/json'
            }
        });
       
        response.then(result =>
            this.setState({ parts: result.data != 'Precarious Service is taking longer than expected. Please try again later' ? result.data['content'] : [] })
        );
    }


    render() {
        return (
            <>
                <div class="container-fluid pt-4 px-4">
                    <div class="row g-4">
                        <div class="col-sm-12 col-xl-12">
                            <div className="col-sm-12 col-xl-12" style={{ textAlign: 'center' }}>
                                <h1 >Histórico de Compras</h1>
                            </div>
                            <div class="bg-light rounded h-100 p-4" style={{ display: 'center' }}>
                                <div className="col-sm-12 col-xl-12" style={{ textAlign: 'left', display: 'flex' }}>
                                    <div className="col-sm-6 col-xl-6" style={{ textAlign: 'left', display: 'flex' }}>
                                        <h6 class="mb-4">Histórico de Compras </h6>
                                    </div>
                                </div>
                                <table class="table">
                                    <thead>
                                        <tr>
                                        <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Código</th>
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Valor</th>
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Data</th>
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Estado</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {this.state.parts.map((part, index) => (

                                            <>
                                                {this.sum(part)}
                                                <tr>
                                                    <>
                                                    <th style={{ textAlign: 'center' }}>{part.itemCar}</th>
                                                        <th style={{ textAlign: 'center' }}>{formatValueWithRegex(part.price)}€</th>
                                                        <th style={{ textAlign: 'center' }}>{this.formatDate(part.date)}</th>
                                                        <th style={{ textAlign: 'center' }}>{this.capitalizeFirstLetter(part.state.toLowerCase())}</th>
                                                    </>

                                                </tr>
                                            </>
                                        ))}
                                    </tbody>
                                </table>

                                <div class="d-flex flex-md-row flex-column" style={{ marginTop: 30, marginLeft: 30 }}>
                                    <h5 className="text">Valor total investido : {formatValueWithRegex(this.state.totalCost)} €</h5>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </>
        )
    }
}


export default Purchases

