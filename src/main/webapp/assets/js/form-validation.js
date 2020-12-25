
let student_form = document.getElementById('student-validation');
// let course_form = document.getElementById('course-validation');
// window.onload = fetch_courses;
if(student_form) {
    student_form.addEventListener('submit', async (e) => {
        e.preventDefault();
        e.stopPropagation();
        let loading=document.getElementById('loading');
        loading.style.display='block';
        if (student_form.checkValidity() === true) {
            let response = await fetch('api/students/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify({
                    email: document.getElementById('email').value,
                })
            });
            let result = await response;
            let email = document.getElementById('email').value;
            let logindiv=document.getElementById('login');
            console.log(result);
            if (result['status'] == 200) {
                console.log("In else :" + result['status']);
                window.localStorage.setItem("email_id", email);
                window.location = "Course.html";

                //fetch_courses(email);
            } else {
                loading.style.display='none';
                // let logindiv = document.getElementById('login');
                logindiv.style.display = "block";
            }
        }
        // student_form.classList.add('was-validated');
    });
}

function encode(data){
    let query=data.url;
    query+='?'+encodeURI("email_id")+'='+encodeURI(data.params.email_id);
    return query;
}
let faculty;
let prereq;
let done;
async function fetch_courses(email){
    data={
        url:'api/students',
        params:{
            email_id:email
        }
    }
    // let response = await fetch(encode(data));
    // let master =  await response.json(); // read response body and parse as JSON
    //console.log(master);
    // var temp=JSON.stringify(master);

    fetch(encode(data)).then(response=>response.json().then(data=>({
        data: data,
        status:response.status
    })
    ).then(res=>{
        faculty=res.data.fac;
        done=res.data.done;
        prereq=res.data.preq;
        console.log(faculty);
        console.log(prereq);
        console.log(done);
        var courses=[];
        for(var i in faculty)
        {
            if(faculty[i].capacity)
            courses.push(i)
        }

        var eligible=[];
        for(var i in courses)
        {
            var flag=1;
            for(var j in prereq[courses[i]])
            {
                for(var k in done)
                {
                    flag=0;
                    if(prereq[courses[i]][j]==done[k])
                    {
                        flag=1;
                        break;
                    }
                }
                if(flag==0) break;
            }
            if(flag==1)
            eligible.push(courses[i])
        }
        console.log(eligible);
        window.location="Course.html";
 
    }));

}
