import React from 'react';
import api from '../../service/api';

class PartsStory extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            parts: [],
            token: "",
            showParts: true,
            showInterior: false,
            showWheels: false,
            showEngine: false
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

    componentDidMount() {
        const response = api.get("/prices/itemprices", {
            headers: {
                Authorization: `Bearer ${this.state.token}`,
                'Content-Type': 'application/json'
            }
        });

        response.then(result => this.setState({ parts: result.data != 'Precarious Service is taking longer than expected. Please try again late' ? result.data : [] }));
    }


    render() {
        return (
            <>
                <div /* key={this.state.showBrakes} */ class="container-fluid pt-4 px-4">
                    <div class="row g-4">
                        <div class="col-sm-12 col-xl-12">
                            <div className="col-sm-12 col-xl-12" style={{ textAlign: 'center' }}>
                                <h1 >Histórico de Vendas</h1>
                            </div>
                            <div class="bg-light rounded h-100 p-4" style={{ display: 'center' }}>
                                <div className="col-sm-12 col-xl-12" style={{ textAlign: 'left', display: 'flex' }}>
                                    <div className="col-sm-6 col-xl-6" style={{ textAlign: 'left', display: 'flex' }}>
                                        <h6 class="mb-4">Histórico de Vendas </h6>
                                    </div>
                                    <div className="col-sm-6 col-xl-6" style={{ textAlign: 'right', float: 'right' }}>
                                        <select onChange={this.formatView.bind(this)} className="form-select" id="floatingSelect" aria-label="Floating label select example" style={{ width: 250, display: 'flex', float: 'right' }}>
                                            <option value={'parts'}>Peças</option>
                                            <option value={'wheels'}>Pneus</option>
                                        </select>
                                    </div>
                                </div>
                                <table class="table">
                                    <thead>

                                        <tr>
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Categoria</th>
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Estado</th>
                                            {this.state.showParts &&
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Descrição</th>
                                            }
                                            {this.state.showWheels &&
                                                <>
                                                    <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Dimensão</th>
                                                    <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Marca</th>
                                                </>
                                            }
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Data de venda </th>
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Preço Estimado </th>
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Preço de venda </th>
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Balanço </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {this.state.parts.map((part, index) => (
                                            <>
                                                {this.state.showParts &&
                                                    <tr>

                                                        {part.description.hasOwnProperty('descrição') &&
                                                            <>
                                                                <th style={{ textAlign: 'center' }}>{part.category['name']}</th>
                                                                <th style={{ textAlign: 'center' }}>{part.itemState['percentage']}%</th>
                                                                <th style={{ textAlign: 'center' }}>{this.formatDescription(part.description)}</th>
                                                                <th style={{ textAlign: 'center' }}>{this.formatDate(part.saleDate)}</th>
                                                                <th style={{ textAlign: 'center' }}>{part.marketPrice}€</th>
                                                                <th style={{ textAlign: 'center' }}>{part.price}€</th>
                                                                {part.price > part.marketPrice &&
                                                                    <td style={{ textAlign: 'center' }}>  <img src="260211.png" style={{ height: '27px' }} /></td>
                                                                }
                                                                {part.price < part.marketPrice &&
                                                                    <td style={{ textAlign: 'center' }}> <img src="1791456.png" style={{ height: '27px' }} /></td>
                                                                }
                                                            </>
                                                        }
                                                    </tr>
                                                }
                                                {this.state.showWheels &&
                                                    <tr>
                                                        {part.description.hasOwnProperty('dimensão') &&
                                                            <>
                                                                <th style={{ textAlign: 'center' }}>{part.category['name']}</th>
                                                                <th style={{ textAlign: 'center' }}>{part.itemState['percentage']}%</th>
                                                                <th style={{ textAlign: 'center' }}>{this.formatDimension(part.description)}</th>
                                                                <th style={{ textAlign: 'center' }}>{this.formatBrand(part.description)}</th>
                                                                <th style={{ textAlign: 'center' }}>{this.formatDate(part.saleDate)}</th>
                                                                <th style={{ textAlign: 'center' }}>{part.price}€</th>
                                                                <th style={{ textAlign: 'center' }}>{part.marketPrice}€</th>
                                                                {part.price > part.marketPrice &&
                                                                    <td style={{ textAlign: 'center' }}>  <img src="260211.png" style={{ height: '27px' }} /></td>
                                                                }
                                                                {part.price < part.marketPrice &&
                                                                    <td style={{ textAlign: 'center' }}> <img src="1791456.png" style={{ height: '27px' }} /></td>
                                                                }
                                                            </>

                                                        }
                                                    </tr>
                                                }
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


export default PartsStory

