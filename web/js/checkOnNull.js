function AdminUser(data) {
    if (data.login.value == 0)
    {
        alert('Заполните поле "Логин"');
        return false;
    }
    
    if (data.fio.value == 0)
    {
        alert('Заполните поле "ФИО"');
        return false;
    }
    
    if (data.email.value == 0)
    {
        alert('Заполните поле "E-mail"');
        return false;
    }
}

function AdminDepart(data) {
    if (data.nameDepart.value == 0)
    {
        alert('Заполните поле "Название"');
        return false;
    }
}

function DoneInc(data) {
    if (data.decision.value == 0)
    {
        alert('Заполните поле "Решение"');
        return false;
    }
}

function CancelInc(data) {
    if (data.textc.value == 0)
    {
        alert('Заполните поле "Причина"');
        return false;
    }
}

function Comment(data) {
    if (data.textcomm.value == 0)
    {
        alert('Введите сообщение');
        return false;
    }
}

function AdminPost(data) {
    if (data.namePost.value == 0)
    {
        alert('Заполните поле "Название"');
        return false;
    }
}

function AdminTI(data) {
    if (data.nameTypeIncident.value == 0)
    {
        alert('Заполните поле "Название"');
        return false;
    }
}

function ManagerTask(data) {
    if (data.title.value == 0)
    {
        alert('Заполните поле "Заголовок"');
        return false;
    }
    
    if (data.texti.value == 0)
    {
        alert('Заполните поле "Текст задания"');
        return false;
    }
    
    if (data.typId.value == "Выберите тип задания")
    {
        alert('Выберите "Тип задания"');
        return false;
    }
}

function UserIncident(data) {
    if (data.title.value == 0)
    {
        alert('Заполните поле "Заголовок"');
        return false;
    }
    
    if (data.texti.value == 0)
    {
        alert('Заполните поле "Текст обращения"');
        return false;
    }
    
    if (data.typId.value == "Выберите тип обращения")
    {
        alert('Выберите "Тип обращения"');
        return false;
    }
}

function SpecZamena(data) {
    if (data.prich.value == 0)
    {
        alert('Заполните поле "Причина замены"');
        return false;
    }
    
    if (data.hw_off.value == 0)
    {
        alert('Заполните поле "Оборудование"');
        return false;
    }
    
    if (data.hw_on.value == 0)
    {
        alert('Выберите "Оборудование на замену"');
        return false;
    }
}

function UserIncidentAtt(data) {
    if (data.title.value == 0)
    {
        alert('Заполните поле "Заголовок"');
        return false;
    }
    
    if (data.texti.value == 0)
    {
        alert('Заполните поле "Текст обращения"');
        return false;
    }
    
    if (data.typId.value == "Выберите тип обращения")
    {
        alert('Выберите "Тип обращения"');
        return false;
    }
    
    if (data.file.value == 0)
    {
        alert('Выберите "Файл"');
        return false;
    }
}