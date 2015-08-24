/**
 * Created by nektodev on 23.08.15.
 */

// Models
window.Artist = Backbone.Model.extend();

window.ArtistCollection = Backbone.Collection.extend({
    model: Artist,
    url:"http://localhost:8080/api/artist"
});

// Views
window.ArtistListView = Backbone.View.extend({

    initialize:function () {
        this.template = _.template(tpl.get('artist-list'));
        this.model.bind("reset", this.render, this);
    },

    render:function (eventName) {
        var child = $(this.el);
        _.each(this.model.models, function (artist) {
            child.append(new ArtistListItemView({model:artist}).render().el);
        }, this);

        $(this.el).html(this.template({'child':child.html()}));
        return this;
    }
});

window.ArtistListItemView = Backbone.View.extend({

    tagName:"li",

    initialize:function () {
        this.template = _.template(tpl.get('artist-list-item'));
    },

    render:function (eventName) {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }

});

window.ArtistView = Backbone.View.extend({

    initialize:function () {
        this.template = _.template(tpl.get('artist-details'));
    },

    render:function (eventName) {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }

});

// Router
var AppRouter = Backbone.Router.extend({

    routes:{
        "":"list",
        "artist/:id":"artistDetails"
    },

    list:function () {
        artistList = new ArtistCollection();
        artistList.fetch({
            success: function(){
                this.artistListView = new ArtistListView({model:artistList});
                $('#sidebar').html(this.artistListView.render().el);
            }
        });

    },

    artistDetails:function (id) {
        this.artist = artistList.get(id);
        this.artistView = new ArtistView({model:this.artist});
        $('#content').html(this.artistView.render().el);
    }
});



$(document).ready(function() {
    $.material.init();
    tpl.loadTemplates(['artist-list', 'artist-list-item', 'artist-details'], function() {

        app = new AppRouter();
        Backbone.history.start();
    });
});
