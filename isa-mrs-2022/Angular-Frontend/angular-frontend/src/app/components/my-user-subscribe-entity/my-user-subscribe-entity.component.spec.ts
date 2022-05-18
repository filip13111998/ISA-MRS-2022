import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyUserSubscribeEntityComponent } from './my-user-subscribe-entity.component';

describe('MyUserSubscribeEntityComponent', () => {
  let component: MyUserSubscribeEntityComponent;
  let fixture: ComponentFixture<MyUserSubscribeEntityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyUserSubscribeEntityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyUserSubscribeEntityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
