import { Component} from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpService } from '../../services/http.service';
import { AuthService } from '../../services/auth.service';
@Component({
  selector: 'app-dashbaord',
  templateUrl: './dashbaord.component.html',
  styleUrls: ['./dashbaord.component.scss']
})
export class DashbaordComponent {

  
}
// import { Component, OnInit } from '@angular/core';
// import { Router } from '@angular/router';
// import { AuthService } from '../../services/auth.service';
// import { HttpService } from '../../services/http.service';

// @Component({
//   selector: 'app-dashbaord',
//   templateUrl: './dashbaord.component.html',
//   styleUrls: ['./dashbaord.component.scss']
// })

// export class DashbaordComponent implements OnInit{
//   roleName: string|null;
//   // errorMessage:any;
//   // statename:any={};
//   constructor(private http:HttpService,private router:Router,private auth:AuthService){
//     this.roleName=this.auth.getRole();
//   }
//   ngOnInit(): void {
    
//   }

//   // dashbaordView(){
//   //   this.http.get
//   // }



// }

