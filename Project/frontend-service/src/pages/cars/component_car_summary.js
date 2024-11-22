import React, { useState } from "react";
import { useEffect } from 'react'
import api from "../../service/api";
import { formatValueWithRegex } from '../../global/utils'

export default function CarSummary(props) {

  const [valueWon, setValue] = useState(false);

  useEffect(() => {
   
    const token = localStorage.getItem("token");
    api.get(`/prices/itemprices/car/` + props.id, {
      headers: {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    }).then((result) => {
      setValue(result.data['valueGenerated'])
    });

  }, []);



  return (
    <>
        <div>
            {formatValueWithRegex(valueWon)}€
        </div>
    
    
    </>
  );
}