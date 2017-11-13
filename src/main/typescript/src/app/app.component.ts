import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { Task } from "./task/Task";

export const REST_URL: string = "http://localhost:8080";
export const REST_URL_TASK: string = REST_URL + "/task";

@Component({    
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  taskList: Task[] = [];
  newTask: Task = this.getNewTask();

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.getTask();
  }

  private getNewTask()  {
    return new Task(null, "", "", "", null, null, "");
  }

  private options = {
    headers: new HttpHeaders({"Content-Type": "application/json"})
  }

  public onClear() {
    this.newTask = this.getNewTask();
  }

  public onSubmit() {
    if(this.newTask.id > 0) {
      this.updateTask();
    } else {
      this.addTask();
    }
  }

  public addTask() {
    let body = JSON.stringify(this.newTask);
    this.http.post<Task>(REST_URL_TASK, body, this.options).subscribe(
      res => {
        this.taskList.push(res);
        this.onClear();
      },
      err => {
        console.log("Error occured" + err.message);
      });
  }

  public updateTask() {
    let body = JSON.stringify(this.newTask);
    this.http.put(REST_URL_TASK, body, this.options).subscribe(
      res => {
        this.replace(this.newTask);
        this.onClear();
      },
      err => {
        console.log("Error occured" + err.message);
      });
  }

  private replace(task: Task) {
    for(let i=0; i<this.taskList.length; i++) {
      if(task.id == this.taskList[i].id) {
        this.taskList[i] = task;
        return;
      }
    }
  }

  public onDelete(index) {
    let id = this.taskList[index].id;
    this.http.delete(REST_URL_TASK + "/" + id, this.options).subscribe(res => {},err => {});
    this.taskList.splice(index, 1);  
  }

  public getTask() {
    this.http.get<Task[]>(REST_URL_TASK).subscribe(
      data => {
        for(let i=0; i<data.length; i++) {
          let task = data[i];
          console.log(task);
          this.taskList.push(task);
      }
    });
  }

  public onEdit(index) {
    this.newTask = this.taskList[index];
  }
}
