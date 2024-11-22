import React from "react";

import api from "../../service/api";
import { useState } from 'react';
import { useEffect } from 'react'
import { projectStorage } from "../../configs/config";

function Login() {

  const [cars, setCars] = useState([]);
  const [categories, setCategories] = useState([]);
  const [itemStates, setItemStates] = useState([]);
  const [employees, setEmployees] = useState([]);
  const [itemSku, setItemSku] = useState('');
  const [price, setPrice] = useState(0);

  const [carId, setCarId] = useState('');
  const [categoryId, setcategoryId] = useState('');
  const [itemState, setItemState] = useState('');
  const [employeeId, setEmployeeId] = useState('');
  const [image, setImage] = useState(null);
  const [url, setUrl] = useState("");
  const [progress, setProgress] = useState(0);
  const [showDescription, setDescription] = useState(true);
  const [dimension, setDimension] = useState('');
  const [descriptionValue, setDescriptionValue] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    const token = localStorage.getItem("token");
    api.get("/purchases/cars/", {
      headers: {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    }).then((result) => {
      setCars(result.data['content'] != undefined ? result.data['content'] : [])
    });

  }, []);

  useEffect(() => {
    const token = localStorage.getItem("token");
    api.get("/cardisassembly/category", {
      headers: {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    }).then((result) => {
      console.log("Result category", result.data)
      setCategories(result.data != 'Car disassembly Service is taking longer than expected. Please try again late' ? result.data : [])
    });

  }, []);

  useEffect(() => {
    const token = localStorage.getItem("token");
    api.get("/users/employees", {
      headers: {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    }).then((result) => {
      setEmployees(result.data['content'] != undefined ? result.data['content'] : [])

    });

  }, []);

  useEffect(() => {
    setItemStates([10, 20, 30, 40, 50, 60, 70, 80, 90, 100])

  }, []);


  const formatCarDescription = (car) => {
    return car['brand'] + "\n" + car['model'] + "  Vin: " + car['vin']

  }

  const handleDimension= (value) =>{
    setDimension(value)
  }

  
  const handleDescriptionValue = (value) =>{
    setDescriptionValue(value)
  }


  const handleSku = (value) => {
    setItemSku(value);
  };

  const handlePrice = (value) => {
    setPrice(value);
  };

  const handleCarId = (value) => {
    console.log("Value ", value)
    setCarId(value);
  };

  const handleCategoryId = (value) => {
    if(value == 8){
      setDescription(false)
      setDescriptionValue('')
    }else{
      setDescription(true)
      setDimension('')
    }
    
    setcategoryId(value);
  };

  const handlePhoto = e => {
    if (e.target.files[0]) {
      setImage(e.target.files[0]);
    }
  };

  const postPart = async e => {
    e.preventDefault();

    let isToPost = true;

    if (itemSku == "") {
      setError('Atribua um  sku!');
      isToPost = false;
    }


    if (image == null) {
      setError('Faça o upload da foto')
    } else {
      const uploadTask = projectStorage.ref(`images/${image.name}`).put(image);
      await uploadTask.on(
        "state_changed",
        snapshot => {
          const progress = Math.round(
            (snapshot.bytesTransferred / snapshot.totalBytes) * 100
          );
          setProgress(progress);
        },
        error => {
          console.log(error);
        },
        () => {
          projectStorage
            .ref("images")
            .child(image.name)
            .getDownloadURL()
            .then(async url => {
              setUrl(url);
              const dataToInsert = JSON.stringify({
                carId: carId,
                categoryId: categoryId,
                itemSku: itemSku,
                photoPath: url,
                employeeId: employeeId,
                description: dimension == '' ?  { descrição: descriptionValue} :  { dimensão: descriptionValue},
                price: price,
                state: itemState,
              })
              
            const response = await api.post("/cardisassembly/itemcar", dataToInsert, {
                headers: {
                  Authorization: `Bearer ${localStorage.getItem("token")}`, 'Accept': 'application/json',
                  'Content-Type': 'application/json'
                }
              }).catch( (error)=>{
                setError("Erro ao criar peça")
              });
              if (response.status == 200 || response.status == 201) {
                setError("Peça criada com sucesso!");
              } else {
                setError("Campos Inválidos");
              } 
            });
        }
      );

    }

  }



  return (
    <>
      <div className="container-fluid pt-4 px-4">
        <div className="col-sm-12 col-xl-12" style={{ textAlign: 'center' }}>
          <h1 >Adicionar nova peça </h1>
        </div>
        <div className="row g-4">
          <div className="col-sm-12 col-xl-12">
            <div className="bg-light rounded h-100 p-4">
              <img src="img/add-user.png" style={{ height: 50 }} />
              <form>
                <div className="mb-3">
                  <label htmlFor="exampleInputEmail1" className="form-label" >Item Sku</label>
                  <input type="name" className="form-control" required id="text" onChange={e => handleSku(e.target.value)} />
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
                      handlePrice(e.target.value)
                    }
                  />
                </div>

                <div className="form-floating mb-3">
                  <select onChange={(e) => handleCarId(e.target.value)} className="form-select" id="floatingSelect" aria-label="Floating label select example">
                    <option selected></option>
                    {cars.map((car) => (
                      <>
                        <option value={car.id}>{formatCarDescription(car)}</option>

                      </>
                    ))}
                  </select>
                  <label htmlFor="floatingSelect">Carro do qual a peça saiu </label>
                </div>

                <div className="form-floating mb-3">
                  <select onChange={(e) => handleCategoryId(e.target.value)} className="form-select" id="floatingSelect" aria-label="Floating label select example">
                    <option selected></option>
                    {categories.map((category) => (
                      <>
                        <option value={category.id}>{category.name}</option>

                      </>
                    ))}
                  </select>
                  <label htmlFor="floatingSelect">Categoria </label>
                </div>


                <div className="form-floating mb-3">
                  <select onChange={(e) => setItemState(e.target.value)} className="form-select" id="floatingSelect" aria-label="Floating label select example">
                    <option selected></option>
                    {itemStates.map((itemState) => (
                      <>
                        <option value={itemState}>{itemState}</option>

                      </>
                    ))}
                  </select>
                  <label htmlFor="floatingSelect">Estado da peça </label>
                </div>
               {showDescription == true &&
                  <>
                    <div className="mb-3">
                      <label htmlFor="exampleInputEmail1" className="form-label" >Descrição</label>
                      <input type="name" className="form-control" required id="text" onChange={e => handleDescriptionValue(e.target.value)} />
                    </div>
                  </>

                }
                   {showDescription == false &&
                  <>
                    <div className="mb-3">
                      <label htmlFor="exampleInputEmail1" className="form-label" >Dimensões</label>
                      <input type="name" className="form-control" required id="text" onChange={e => handleDimension(e.target.value)} />
                    </div>
                  </>

                }
                <div className="form-floating mb-3">
                  <select onChange={(e) => setEmployeeId(e.target.value)} className="form-select" id="floatingSelect" aria-label="Floating label select example">
                    <option selected></option>
                    {employees.map((employee) => (
                      <>
                        <option value={employee.id}>{employee.firstName + " "}  {employee.lastName} </option>

                      </>
                    ))}
                  </select>
                  <label htmlFor="floatingSelect">Pessoa que desmontou</label>
                </div>

                <div className="form-floating mb-3">
                  <input onChange={handlePhoto} type="File" />
                </div>
                <button className="btn btn-primary" onClick={postPart} style={{ width: 140 }}>Registar peça </button>
                <h3 class="text-center mb-0">{error}</h3>
              </form>

            </div>
          </div>

        </div>
      </div></>
  );
}

export default Login;

