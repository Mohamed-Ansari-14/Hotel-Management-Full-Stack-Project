import { Component } from '@angular/core';
import { CustomerService } from '../../customer-services/customer.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-view-bookings',
  templateUrl: './view-bookings.component.html',
  styleUrls: ['./view-bookings.component.scss']
})
export class ViewBookingsComponent {

  currentPage: any = 1;

  total: any;
  bookings: any;

  constructor(
    private customerService: CustomerService,
    private message: NzMessageService,
  ){
    this.getBookings();
  }

  getBookings(){
    this.customerService.getMyBookings(this.currentPage - 1).subscribe(res => {
      console.log(res); 
      
      this.bookings = res.reservationDtoList;
      this.total = res.totalPages * 5;
    }, errror => {
      this.message.error(`${errror.error}`, { nzDuration: 5000 })
    })
  }

  pageIndexChange(value: any){
    this.currentPage = value;
    this.getBookings();
  }

}
