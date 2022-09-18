import React,{useEffect, useState} from 'react'
import { Link, useParams,useNavigate } from "react-router-dom";
import Connection from './Connection'
const SupplierDetails = () => {
    const { id } =useParams()
    const navigate=useNavigate()
  const [customer,setcustomerDetails] = useState([])
  const [user,setUser] = useState([])
  
  const [role,setRole]=useState()
  const [isAccountNonExpired,setisAccountNonExpired] = useState()
  const [isEnabled,setisEnabled] = useState('1')
  const [isCredentialsNonExpired,setisCredentialsNonExpired] = useState('1')
  const [isAccountNonLocked,setisAccountNonLocked] = useState('1')
  const [aadhaar,setAadhaar] = useState('1')
  useEffect(() => {
    Connection.getSupplier(id).then((response)=> {
        setcustomerDetails(response.data.supplier)
        setUser(response.data.user)
        
        setAadhaar(response.data.user.id)
        setRole(response.data.user.role)
   }).catch(error =>{
       console.log(error);
   })
  },[])
  const BackToList=(e)=>{
    navigate('/supplierlist')
  }
  const saveChanges=(e)=>{ 
    const users={aadhaar,role,isAccountNonExpired,isAccountNonLocked,isCredentialsNonExpired,isEnabled}
   
    Connection.EditUser(users).then((response)=>{      
        navigate('/supplierlist')
    }).catch(()=>{
            alert("Something Went Wrong")
    })
   
  }

    return (
        <div>
        <div>

            <div className="container col-8 mt-5 mb-5" >


                <div className='card text-bg-light p-1' >
                    <p >
                        <form method="post " className='container-center col-8' > 
                            <div class="row">
                            </div>
                            <div class="row">
                                <div className='offset-4'>
                                    <div class="col-md-8">
                                        <div class="tab-content profile-tab" id="myTabContent">
                                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                            <h3 className='text-center'>Supplier_Details</h3><hr></hr>

                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <label class="col-mb-2">User ID</label>
                                                    </div>
                                                    <div class="col-md-6">
                                                        
                                                        <p class="text-success">{user.id}</p>
                                                    </div>
                                                </div>    
                                            </div>
                                                <div class="row">
                                                <div class="col-md-6">
                                                    <label>Supplier Name</label>
                                                </div>
                                                <div class="col-md-6">
                                                    
                                                    <p class="text-success">{user.name}</p>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Mobile</label>
                                                </div>
                                                <div class="col-md-6">
                                                    
                                                    <p class="text-success">{user.mobile}</p>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Email</label>
                                                </div>
                                                <div class="col-md-6">
                                                    
                                                    <p class="text-success">{user.username}</p>
                                                </div>
                                            </div>
                                                <div class="row">
                                                <div class="col-md-6">
                                                    <label> Address</label>
                                                </div>
                                                <div class="col-md-6">
                                                   
                                                    <p class="text-success">{customer.address}</p>
                                                </div>
                                            </div>
                                            
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Pincode</label>
                                                </div>
                                                <div class="col-md-6">
                                                  
                                                    <p class="text-success">{customer.pincode}</p>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Aadhaar Card</label>
                                                </div>
                                                <div class="col-md-6">
                                                   
                                                    <p class="text-success">{customer.aadhaar}</p>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Date Of Birth</label>
                                                </div>
                                                <div class="col-md-6">
                                                   
                                                    <p class="text-success">{new Date(customer.dob).toDateString()}</p>
                                                </div>
                                            </div>
                                            
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Service</label>
                                                </div>
                                                <div class="col-md-6">
                                                   
                                                    <p class="text-success">{customer.serviceType.name}</p>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Charge</label>
                                                </div>
                                                <div class="col-md-6">
                                                   
                                                    <p class="text-success">{customer.charge}</p>
                                                </div>
                                            </div>
                                            <div class="row mt-3">
                                                <div class="col-md-6">
                                                    <label>Update Role</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <select onChange={e=>{setRole(e.target.value)}} className='text-success' name="role">
                                                        <option value={'ROLE_SUPPLIER'} selected>Supplier</option>
                                                        {/* <option value={'ROLE_SUPPLIER'}>ADMIN</option> */}
                                                        <option value={'ROLE_ADMIN'}>ADMIN</option>

                                                    </select>
                                                </div>
                                            </div>

                                            <div class="row mt-3">
                                                <div class="col-md-6">
                                                    <label>Account Enable</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <select className='text-success' onChange={e=>{setisEnabled(e.target.value)}} name="isEnabled">
                                                        <option value={'1'} selected>Yes</option>
                                                        <option value={'0'}>No</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row mt-3">
                                                <div class="col-md-6">
                                                    <label>Account Expired </label>
                                                </div>
                                                <div class="col-md-6">
                                                    <select className='text-success'  onChange={e=>{setisCredentialsNonExpired(e.target.value)}} name="isCredentialsNonExpired">
                                                        
                                                        <option value={'0'}>Yes</option>
                                                        <option  value={'1'} selected>No</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row mt-3">
                                                <div class="col-md-6">
                                                    <label>Account Locked </label>
                                                </div>
                                                <div class="col-md-6">
                                                    <select className='text-success'  onChange={e=>{setisAccountNonLocked(e.target.value)}} name="isAccountNonLocked">
                                                        
                                                        <option value={'0'}>Yes</option>
                                                        <option  value={'0'} selected>No</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row mt-3">
                                                <div class="col-md-6">
                                                    <label>Credential Expired</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <select className='text-success'  onChange={e=>{setisAccountNonExpired(e.target.value)}} name="isCredentialsNonExpired">
                                                        
                                                        <option value={'0'}>Yes</option>
                                                        <option  value={'1'} selected>No</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </form>
                    <div className='offset-3 mt-5'>
                        <tr >

                            <button  onClick={e=>{saveChanges(e)}}
                            className="btn btn-outline-primary  col-3  mt-3 rounded-pill mb-3 " style={{
                                width: 200,
                                height: 50,
                                borderRadius: 140 / 2,
                               // backgroundColor: '#16A5A5',
                                transform: [
                                    { scaleX: 3 }
                                ]
                            }} >Save Changes</button>

                            
                            <button onClick={e=>{BackToList(e)}}
                            className="btn btn-outline-primary col-3  ms-3 mt-3 rounded-pill mb-3" style={{
                                width: 200,
                                height: 50,
                                borderRadius: 140 / 2,
                               // backgroundColor: '#D33115',
                                transform: [
                                    { scaleX: 3 }
                                ]
                            }} >Back To List</button>

                        </tr></div>
                    </p>
                </div>
            </div>

        </div>
        
    </div>


    )
}

export default  SupplierDetails