import { Component } from '@angular/core';
import { CustomerService } from '../../customer-services/customer.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { UserStorageService } from 'src/app/auth/services/storage/user-storage.service';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.scss']
})
export class RoomsComponent {

  currentPage = 1;
  rooms = [];
  total: any;

  constructor(
    private customerService: CustomerService,
    private message: NzMessageService,
    private modalService: NzModalService,
  ){
    this.getRooms();
  }

  getRooms(){
    this.customerService.getRooms(this.currentPage - 1).subscribe(res => {
      console.log(res);
      this.rooms = res.roomDtoList;
      this.total = res.totalPages * 1;
    })
  }

  pageIndexChange(value: any){
    this.currentPage = value;
    this.getRooms();
  }

  isVisibleMiddle = false;
  date: Date[] = [];
  checkInDate: Date;
  checkOutDate: Date;
  id: number;

  onChange(result: Date[]){
    if(result.length === 2){
      this.checkInDate = result[0];
      this.checkOutDate = result[1];
    }
  }

  handleCancelMiddle(){
    this.isVisibleMiddle = false;
  }

  handleOkMiddle(): void{
    const obj = {
      userId: UserStorageService.getUserId(),
      roomId: this.id,
      checkInDate: this.checkInDate,
      checkOutDate: this.checkOutDate
    }

    this.customerService.bookRoom(obj).subscribe(res => {
      this.message.success(`Request Submitted for approval!`, { nzDuration: 5000 });
      this.isVisibleMiddle = false;
    }, error => {
      this.message.error(`${error.error}`, { nzDuration: 5000 });
    })
  }

  showModalMiddle(id: number){
    this.id = id;
    this.isVisibleMiddle = true;
  }
}
