import { ChangeDetectorRef, Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from '../../admin-services/admin.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.scss']
})
export class ReservationsComponent {

  currentPage: any = 1;
  total: any;
  // reservations: any[] = [];
  reservations: any;

  constructor(
    private adminService: AdminService,
    private message: NzMessageService,
    // private cdr: ChangeDetectorRef
  ){
    this.getReservations();
  }

  getReservations(){
    this.adminService.getReservations(this.currentPage -1 ).subscribe(res => {
      console.log('Full API Response: ',res);
      console.log('Expected Data', res.reservationsDtoList);     
      // console.log('Reservations before assignment: ', this.reservations);
      // this.reservations = res.reservationsDtoList || [];
      this.reservations = res.reservationDtoList
      console.log('Reservations after assignment: ', this.reservations);
      this.total = res.totalPages * 5;
      // this.cdr.detectChanges();
    })
  }

  pageIndexChange(value: any){
    this.currentPage = value;
    this.getReservations();
  }

  changeReservationStatus(bookingId: number, status: string){
    this.adminService.changeReservationStatus(bookingId, status).subscribe(res => {
      this.message.success(`Reservation status updated successfully`, {nzDuration: 5000});
      this.getReservations();
    }, error => {
      this.message.error(`${error.error}`, {nzDuration: 5000});
    })
  }

}
