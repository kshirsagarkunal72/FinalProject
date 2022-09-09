import React, { Component } from 'react'
import { Link } from "react-router-dom";


export default class CCancelledOrders extends Component {
    render() {
        return (
            <div className="container col-6 mt-5 mb-5">


<div className='card text-bg-light p-3 '>
    <form>
        <h3 className='text-center'>Cancelled Orders</h3><hr></hr>
        <div className="form-group mb-2">
            <label className="form-label mt-2">Supplier Name:</label>
            <span class="offset-1">Dynamic Electricals</span>
        </div>
        <div className="form-group mb-2">
            <label className="form-label mt-2">Service:</label>
            {/* <span class="offset-1">Dynamic Electricals</span> */}
            <span class="badge rounded-pill text-bg-warning offset-3"><h6>Electrical</h6></span>
        </div>

        <div className="form-group mb-2">
            <label className="form-label mt-2">Order Date:</label>
            <span class="offset-2">09-09-2022</span>
        </div>
        <div className="form-group mb-2">
            <label className="form-label mt-2">Description:</label><br></br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span class="offset-1">Old Mixture having problem with motor</span>
        </div>
        <div className='d-grid gap-2'>
            <tr>
            <Link to={`/`}><button className="btn btn-primary mt-3 rounded-pill offset-5">Back</button></Link>
            </tr>
        </div>
    </form>
</div>
</div>

        )
    }
}