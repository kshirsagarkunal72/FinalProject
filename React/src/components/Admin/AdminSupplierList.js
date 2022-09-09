import React,{useEffect, useState}  from 'react'
import {Link} from 'react-router-dom'
import Connection from '../Services/Connection'

const AdminSupplierList = () => {
    const [suppliers,setSupplier] = useState([])

    useEffect(() => {
        Connection.getAllUsers().then((response)=> {
            setSupplier(response.data)
           console.log(response.data)
       }).catch(error =>{
           console.log(error);
       })
    },[])
  return (
    <div className="container">
      <h2 className="text-center">Supplier's List</h2>
      <table className="table table-bordered table-striped">
          <thead>
              <th>Supplier Id</th>
              <th>Supplier Name</th>
              <th>PAN No</th>
              <th>Supplier Email-Id</th>
              <th>Actions</th>
          </thead>
          <tbody>
              {
                 suppliers.map(
                      sup =>
                      <tr key={sup.id}>
                          <td>{sup.id}</td>
                          <td>{sup.firstName}</td>
                          <td>{sup.lastName}</td>
                          <td>{sup.emailId}</td>
                          <td>
                              <Link to={``}><button className="btn btn-success" >Details</button></Link>
                          </td>
                          
                      </tr>
                  )
              }
          </tbody>
      </table>
      <Link to={`/adashboard`}><button className="btn btn-success" >Back To DashBoard</button></Link>
    </div>
    )
}

export default AdminSupplierList