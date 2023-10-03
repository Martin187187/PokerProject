import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WebocketComponent } from './webocket.component';

describe('WebocketComponent', () => {
  let component: WebocketComponent;
  let fixture: ComponentFixture<WebocketComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WebocketComponent]
    });
    fixture = TestBed.createComponent(WebocketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
