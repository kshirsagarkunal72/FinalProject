import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {NavBar} from './components/Shared/NavBar';
import {Banner} from './components/Shared/Banner';
import {Skills} from './components/Shared/Skills';
import {Projects} from './components/Shared/Projects';
import {Footer} from './components/Shared/Footer';
import {Routes,Route } from "react-router-dom";
import JoinUs from './Users/JoinUs';
import SignIn from './Users/SignIn';
import SignUp from './Users/SignUp';
import ContactUs from './Users/ContactUs';
import VerifyOtp from './Users/VerifyOTP';
import NewPassword from './Users/NewPassword';
import Home from './components/Shared/Home';
import SDashBoard from './components/Supplier/SDashBoard';
import ADashBoard from './components/Admin/ADashBoard';
import AdminProfile from './components/Admin/AdminProfile';
import AdminCustomerList from './components/Admin/AdminCustomerList';
import AdminSupplierList from './components/Admin/AdminSupplierList';
import AddAdmin from './components/Admin/AddAdmin';
import CDashBoard from './components/Customer/CDashBoard'
import EditProfileC from './components/Customer/CEditProfile'
import CustomerProfile from './components/Customer/ProfileC';
import Card from './Users/Card';
import AdminList from './components/Admin/AdminList';
import OrderList from './components/Admin/OrderList';
function App() {
  return (
    <>
    <NavBar />
      <Routes>
      <Route exact path="/" element={<Home/>}/>
      <Route exact path="/banner" element={<Banner/>}/>
      <Route exact path="/skills" element={<Skills/>}/>
      <Route exact path="/projects" element={<Projects />}/>
      <Route exact path="/joinUs" element={<JoinUs />}/>
      <Route exact path="/signIn" element={<SignIn />}/>
      <Route exact path="/signUp" element={<SignUp />}/>
      <Route exact path="/contactUs" element={<ContactUs/>}/>
      <Route exact path="/sdashboard" element={<SDashBoard/>}/>
      <Route exact path="/adashboard/" element={<ADashBoard/>}/>
      <Route exact path="/adashboard/a/:id" element={<ADashBoard/>}/>
      <Route exact path="/adminList/a/:id" element={<AdminProfile/>}/>
      <Route exact path="/adminCustomerList" element={<AdminCustomerList/>}/>
      <Route exact path="/adminSupplierList" element={<AdminSupplierList/>}/>
      <Route exact path="/adminSupplierList/a/:id" element={<AdminSupplierList/>}/>
      <Route exact path="/Addadmin" element={<AddAdmin/>}/>
      <Route exact path="/editadmin/:id" element={<AddAdmin/>}/>
      <Route exact path="/dashboards" element={<SDashBoard/>}/>
      
      <Route exact path="/dashboardc" element={<CDashBoard/>}/>
      <Route exact path="/editprofilec" element={<EditProfileC/>}/>
      <Route exact path="/verify" element={<VerifyOtp/>}/>
      <Route exact path="/profilec" element={<CustomerProfile/>}/>
      <Route exact path="/setnewpassword" element={<NewPassword/>}/>
      <Route exact path="/card" element={<Card/>}/>
      <Route exact path="/adminList" element={<AdminList/>}/>
      <Route exact path="/OrderList" element={<OrderList/>}/>
      </Routes>
      <Footer />
      </>
  );
}

export default App;
