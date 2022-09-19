import React,{useEffect, useState}  from 'react'
import {Link, useParams,useNavigate} from 'react-router-dom'
import CustConnection from './ConnectionCustomer/CustConnection';


const CSupplierList = () => {
    const { id } =useParams()
    const [order,setOrder] = useState([])
    let navigate = useNavigate()
    const GetOrder=(e,status,id)=>{
        if(status=="New")
                navigate(`/newenquiryc/${id}`)
        if(status=="Pending")
                navigate(`/pendingorderc/${id}`)
         if(status=="Cancalled")
                navigate(`/cancalledorderc/${id}`)
        if(status=="Completed")
                navigate(`/completedorderc/${id}`)
    
        }
       useEffect(() => {
          CustConnection.getOrder().then((response)=> {
            setOrder(response.data.customer.orders)
            
             console.log(response.data)
         }).catch(error =>{
             console.log(error);
         })
      },[])

  return (
    <div className="container">
      <h2 className="text-center">Your Orders</h2>
      <table className="table table-bordered table-striped">
          <thead>
              <th>Order Id</th>
              <th>Description</th>
              <th>Status</th>
              <th>Actions</th>
          </thead>
          <tbody>
              {
                  order.map(
                      sp =>
                      <tr key={sp.orderid}>
                          <td>{sp.orderid}</td>
                          <td>{sp.description}</td>
                          <td>{sp.status}</td>
                          <td>
                          <button className="btn btn-success" type='button' onClick={(e)=>{GetOrder(e,sp.status,sp.orderid)}} >Details</button>
                          </td>
                          
                      </tr>
                  )
              }
          </tbody>
      </table>
      <Link to={``}><button className="btn btn-success" >Back To DashBoard</button></Link>
    </div>
    )
}

export default CSupplierList