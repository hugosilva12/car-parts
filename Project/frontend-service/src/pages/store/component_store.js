import React from 'react';
import './store.css'
import api from '../../service/api';
import { data } from 'jquery';

class Store extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            products: [],
            token: "",
        }
        this.state.token = localStorage.getItem("token");
        document.title = "Loja"
    }

    componentDidMount() {
        const response = api.get("/advertisement/ad", {
            headers: {
                Authorization: `Bearer ${this.state.token}`,
                'Content-Type': 'application/json'
            }
        });

        response.then(result => {
            {
               
                this.setState({ products: result.data['content'] != undefined ? result.data['content'] : [] })
            }
         
        });
    }

    buy = async (product)  => {
        const dataToInsert = {
            client: localStorage.getItem("userAccountId"),
            date: new Date(),
            itemCarId: product.itemCar,
            price: product.price,
        }
        if (dataToInsert.client !== "" && dataToInsert.itemCarId !== "" && dataToInsert.price !== "" && dataToInsert.id != "") {
          const response = await api.post("/sales/sale", JSON.stringify(dataToInsert), {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${localStorage.getItem("token")}`
            },
          });

          console.log('Response ', response)
          if (response.status == 200 || response.status == 201) {
            alert("Comprado!")
            window.location.reload()
          } else {
            alert("Erro ao efetuar compra")
          }
        }
      };

    render() {
        return (
            <>
                <div class="container-fluid pt-4 px-4">
                    <div class="row g-4">
                        <div class="col-sm-12 col-xl-12">
                            <div className="col-sm-12 col-xl-12" style={{ textAlign: 'center' }}>
                                <h1 >Loja</h1>
                            </div>
                            <div class="bg-light rounded h-100 p-4 productsContainer">
                                {this.state.products.map((product, index) => (
                                    <>
                                        <div class="product-card">
                                            <img src={product.photoPath} alt="Imagem da peça" style={{ height: 160 }}/>
                                            <h3>{product.category}</h3>
                                            <p class="price">{product.price}€</p>
                                            <p class="description">{product.itemState}</p>
                                            <button  onClick={() => {
                                                this.buy(product)
                                            }}>Comprar</button>
                                        </div>
                                    </>
                                ))}
                                
                            </div>
                        </div>

                    </div>
                </div>
            </>
        )
    }
}


export default Store
